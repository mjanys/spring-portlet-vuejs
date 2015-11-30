(function (window) {

    Vue.filter('queryString', function (json) {
        // Y.QueryString.stringify(data);
        return Object.keys(json).filter(function (k) {
            return !!k;
        }).map(function (key) {
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
                    urlData: {},
                    urlDataToAdd: {
                        key: null,
                        value: null
                    },
                    urlDataToRemove: {
                        key: null
                    }
                }
            },

            beforeCompile: function() {
                var params = parseLocation(this);
                this.$set('urlData', params);
            },

            methods: {
                addUrlData: function (e) {
                    if (this.urlDataToAdd.key) {
                        this.$set('urlData.' + this.urlDataToAdd.key, this.urlDataToAdd.value);
                    }
                    this.urlDataToAdd.key = null;
                    this.urlDataToAdd.value = null;
                },
                removeUrlData: function (e) {
                    Vue.delete(this.urlData, this.urlDataToRemove.key);
                    this.urlDataToRemove.key = null;
                }
            },

            watch: {
                urlData: {
                    handler: function (data) {
                        this.$log('urlData');
                        if (!Object.keys(this.urlData).length) {
                            window.location.hash = '';
                        }
                        else {
                            window.location.hash = this.$eval("urlData | queryString | hash");
                        }
                    },
                    deep: true
                }
            }
        });
})(window);