
Enabling Offline Application Caching
-------------------------------------

By default, when your servlet extends TouchKitServlet, it tells the browser to
cache all static stuff, so as the application can be started and run when the server
is unreachable.

You can disable Application Caching just annotating your Servlet or your UI classes
with:

  @CacheManifestEnabled(false)

Enabling the OfflineMode client UI
-------------------------------------

TouchKit allows to define a client UI widget which will be shown whenever it detects
that the server is unreacheable. The default implementation is enabled by default,
and it just shows a message telling that the server is not reachable.

You can disable OfflineMode, either: not extending the TouchKitServlet, or annotating
your Servlet or your UI classes with:

  @OfflineModeEnabled(false)

Changing the default OfflineMode implementation
-------------------------------------

Touchkit comes with a default UI client component for offline mode, since it is a default
message telling that the server is unreachable, you might want to change it by a customised
and a more interactive client-only UI. To do that, you only have to create a new class
which must implement the OfflineMode interface, then set these lines in your
${ApplicationName}WidgetSet.gwt.xml module file:

 <replace-with class="your.namespace.YourCustomizedOfflineMode">
  <when-type-is class="com.vaadin.addon.touchkit.gwt.client.offlinemode.OfflineMode" />
 </replace-with>

Deploying your app in a Cordova/Phonegap container
-------------------------------------

Offline/Online events in a web application running in a cordova container
have a lot of issues specially when deployed in android.

In the cordova/ folder you have a script and an html file with everything
set to load your Vaadin aplication in a cordova container and interact with
the app in order to reliably know when the device is online.

After creating a phonegap app with the network plugin enabled (see phonegap site
instructions), replace your index.html by the file in this folder, and
copy the vaadinMobile.js script to the same folder.

Edit the index.html file and modify the vaadinAppUrl variable so as it points
to application url.

This is the most reliable way for handling offline/online events in
Android devices, it is also useful for setting up a top margin in
iOS devices in order that the status bar does not ovelap the view.

