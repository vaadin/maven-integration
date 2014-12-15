#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
window.${package.replace(".", "_")}_samples_AttributeExtension = function() {

    this.onStateChange = function() {
        var element = this.getElement(this.getParentId());
        if (element) {
            var attributes = this.getState().attributes;
            for (var attr in attributes) {
                if (attributes.hasOwnProperty(attr)) {
                    element.setAttribute(attr, attributes[attr]);
                }
            }
        }
    }
}
