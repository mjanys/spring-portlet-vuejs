(function (window) {

    var buildUrl = function (base, key, value) {
        var sep = (base.indexOf('?') > -1) ? '&' : '?';
        return base + sep + key + '=' + value;
    };

    window.PersonsTable = window.PersonsTable || Vue.extend({
            // app initial state
            // can't in extends - it could be shared between instances
            data: function () {
                return {
                    data: [],
                    sortKey: '',
                    sortOrders: {
                        id: 1,
                        name: 1,
                        email: 1,
                        timestamp: 1
                    }
                };
            },

            ready: function () {
                // load data
                this.$http.get(this.$options.dataUrl, function (data, status, request) {
                    // set data on vm
                    this.$set('data', data)
                }).error(function (data, status, request) {
                    // handle error
                    alert('Unable to load ' + this.$options.dataUrl + ' ' + status);
                });
            },

            // methods that implement data logic.
            // note there's no DOM manipulation here at all.
            methods: {
                sortBy: function (key) {
                    this.sortKey = key;
                    this.sortOrders[key] = (this.sortOrders[key] || 1) * -1;
                },
                detail: function (id) {
                    window.location = buildUrl(this.$options.detailUrl, "id", id);
                },
                edit: function (id) {
                    window.location = buildUrl(this.$options.editUrl, "id", id);
                },
                remove: function (id) {
                    window.location = buildUrl(this.$options.deleteUrl, "id", id)
                }
            }
        });
})(window);