(function (window) {

    Vue.filter('queryString', function (json) {
        return Object.keys(json).map(function(key) {
            return encodeURIComponent(key) + '=' + encodeURIComponent(json[key]);
        }).join('&');
    });
    Vue.filter('hash', function (value) {
        return '#' + value;
    });

    var queryStringToJson = function(str) {
        var queryDict = {};
        str.split("&").forEach(function(item) {
            var split = item.split("=");
            queryDict[split[0]] = split[1]
        });
        return queryDict;
    };

    var parseLocation = function() {
        return queryStringToJson(location.hash.substr(1));
    };

    window.UrlState = window.UrlState || Vue.extend({
            data: function() {
                return {
                    urlData: {}
                }
            },

            beforeCompile: function() {
                var params = parseLocation(this);
                this.$set('urlData', params);
            },

            methods: {}
        });
})(window);