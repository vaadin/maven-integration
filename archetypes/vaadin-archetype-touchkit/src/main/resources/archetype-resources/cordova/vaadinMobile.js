/**
 * Script to load a Vaadin App in a Cordova (Phonegap) container.
 *
 * It first validates that Cordova framework and network plugin is available and then
 * inserts the Vaadin app in an iframe or redirects the page if cordova is unavailable.
 *
 * It sends messages to the iframe when network status changes.
 * The iframe approach is necessary in android, since html5 network events in
 * android webview is broken, and webview receives unexpected offline events when
 * the app starts, when the screen orientation changes, when the keyboard is
 * displayed, etc.
 *
 * The iframe is also useful to add a top marging of 20px in ios7 and ios8 to avoid
 * status bar overlapping app view.
 *
 * To use this script, you must define the 'window.vaadinAppUrl' pointing to the address
 * where the app is running, optionally you can define 'window.iframeDisable'
 * to open the app in the parent window, and 'window.iframeTopMargin' to add a
 * margin at top.
 *
 * @author Manuel Carrasco Moñino
 */
(function() {
    // Insert an animated div
    function insertLoading(top) {
        var e = document.createElement('div');
        document.body.appendChild(e);

        var s = e.style;
        s.position = 'fixed';
        s.top = top + 'px';
        s.width = '1%';
        s.left = '1%';
        s.height = '3px';
        s.backgroundColor = '#00b4f0';

        // 15 secs. progress bar which changes to red after 5 secs
        s.webkitTransition = s.transition = 'width 15s cubic-bezier(.23, 1, .32, 1) 0s, background-color 8s linear 5s';
        // we have to force re-flow in order to make transition work
        e.offsetWidth;
        // animate the loading bar
        s.width = '98%';
        s.backgroundColor = 'red';

        return e;
    }
    // Insert the iframe
    function insertIframe(src, top) {
        var e = document.createElement('iframe');
        e.id = 'app';
        e.src = src;
        document.body.appendChild(e);
        var s = e.style;
        s.position = 'fixed';
        s.width = '100%';
        s.left = '0px';
        s.top = top + 'px';
        s.height = top ? 'calc(100% - ' + top + 'px)' : '100%';
        s.border = 'none';
        s.opacity = 0;
        s.webkitTransition = s.transition = 'all .8s ease-out';
        s.webkitTransform = s.transform = 'scale(0)';
        return e;
    }

    function loadVaadinApp(cordovaAvailable) {
        if (!window.vaadinAppUrl) {
            console.error("vaadinMobile.js: ERROR, you must define the 'vaadinAppUrl'.");
            return;
        }
        // Load vaadin in parent window.
        if (window.iframeDisabled) {
            window.location.replace(vaadinAppUrl);
            return;
        }
        // when vaadin app is loaded, it sends to the parent window a ready message
        window.addEventListener('message', function(ev) {
            if (ev.data == 'touchkit-ready'){
                loading.style.display = 'none';
                iframe.style.opacity = 1;
                iframe.style.webkitTransform = 'scale(1)';
                resume();
            }
        }, false);
        // Load Vaadin app in an iframe
        var iframe = document.getElementById('app') || insertIframe(vaadinAppUrl, window.iframeTopMargin || 0);

        if (!window.cordova) {
            console.error("vaadinMobile.js: ERROR cordova framework is not available. Hint: check that you included cordova.js in your index.html.");
            return;
        }
        if (!cordovaAvailable || !navigator.network || !navigator.network.connection || !Connection) {
            console.error("vaadinMobile.js: ERROR cordova network is not available. Hint: check that you included cordova network plugin in your config.xml.");
            return;
        }

        var intervalId;
        function sendMessage(msg) {
            iframe.contentWindow.postMessage("cordova-" + msg, "*");
        }
        function check() {
            var sts = navigator.network.connection.type == Connection.NONE ? 'offline' : 'online';
            sendMessage(sts);
        }
        function resume() {
            // send a message with connection status periodically to the app
            clearInterval(intervalId);
            intervalId = setInterval(check, 30000);
            check();
            sendMessage('resume');
        }
        function pause() {
            clearInterval(intervalId);
            sendMessage('pause');
        }

        // Listen for cordova offline/online events
        document.addEventListener('offline', check, false);
        document.addEventListener('online', check, false);
        document.addEventListener('resume', resume, false);
        document.addEventListener('pause', pause, false);
    }

    // Insert our loading bar if there isn't any customized loading element in the page yet.
    var loading = document.getElementById('loading') || insertLoading(window.iframeTopMargin || 0);

    // Fall back: load the app after a while if Cordova didn't load
    var timeoutId = setTimeout(function(){
        console.error("vaadinMobile.js: ERROR deviceready was not fired after 5 secs. Loading app without Cordova support");
        loadVaadinApp(false);
    }, 5000);

    // Cordova: load the app when cordova begins available
    document.addEventListener('deviceready', function() {
        clearTimeout(timeoutId);
        loadVaadinApp(true);
    }, false);
})();

