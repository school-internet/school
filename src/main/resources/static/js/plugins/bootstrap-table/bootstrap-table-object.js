/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author caojie
 */
(function () {
    var BSTable = function (bstableId, url,uniqueId, columns) {
        this.btInstance = null;                 //jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url =  url;
        this.method = "post";
        this.paginationType = "server";         //默认分页方式是服务器分页,可选项"client"
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.height = 665;                      //默认表格高度665
        this.data = {};
        this.uniqueId = uniqueId;
    };



    BSTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            this.btInstance =
                $('#' + tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    url: this.url,              //请求地址
                    method: this.method,        //ajax方式,post还是get
                    toolbar: "#" + this.toolbarId,//顶部工具条
                    striped: true,              //是否显示行间隔色
                    cache: false,               //是否使用缓存,默认为true
                    pagination: true,           //是否显示分页（*）
                    singleSelect:true,          //是否单选
                    sortable: true,             //是否启用排序
                    sortOrder: "desc",          //排序方式
                    pageNumber: 1,                  //初始化加载第一页，默认第一页
                    pageSize: 20,               //每页的记录行数（*）
                    pageList: [20, 50, 100],    //可供选择的每页的行数（*）
                    queryParamsType: 'limit',   //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParams:oTableInit.queryParams , //传递参数（*）
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: false,              //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    uniqueId:  this.uniqueId,                     //每一行的唯一标识，一般为主键列
                    strictSearch: true,         //设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: false,          //是否显示所有的列
                    clickToSelect: true,        //是否启用点击选中行
                    searchOnEnterKey: true,     //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,      //列数组
                    pagination: true,           //是否显示分页条
                });
            return this;
        },


        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (param) {

        },

        /**
         * 设置分页方式：server 或者 client
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },
        setqueryParams:function () {
            this.queryParams = {
                query:true
            }
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query params specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        }
    };

    window.BSTable = BSTable;

}());

var oTableInit = new Object();
oTableInit.queryParams = function (params){
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        rows: params.limit,   //页面大小
        page: params.offset/params.limit+1  //页码

    };
    $.extend(temp, queryMap);
    return temp;
}
