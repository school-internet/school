(function ($) {
    //1.定义jquery的扩展方法bootstrapSelect
    //班次
    $.fn.teamSelect = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.teamSelect.methods[options](this, param);
        }
        //2.将调用时候传过来的参数和default参数合并
        options = $.extend({}, $.fn.teamSelect.defaults, options || {});
        //3.添加默认值
        var target = $(this);
        if (!target.hasClass("selectpicker")) target.addClass("selectpicker");
        target.attr('valuefield', options.valueField);
        target.attr('textfield', options.textField);
        target.empty();
        var option = $('<option></option>');
        option.attr('value', '');
        option.text(options.placeholder);
        target.append(option);
        //var param = {};
        // options.onBeforeLoad.call(target, options.param);
        if (options.data) {
            init(target, options.data);
        }

        function init(target, data) {
            $.each(data, function (i, item) {
                var option = $('<option></option>');
                option.attr('value', item[options.valueField]);
                option.text(item[options.textField]);
                target.append(option);
            });
            //   options.onLoadSuccess.call(target);
        }
        target.unbind("change");
        target.on("change", function (e) {
            if (options.onChange)
                return options.onChange(target.val());
        });
    }

    //5.如果传过来的是字符串，代表调用方法。
    $.fn.teamSelect.methods = {
        getValue: function (jq) {
            return jq.val();
        },
        setValue: function (jq, param) {
            jq.val(param);
        }

    };

    //6.默认参数列表
    $.fn.teamSelect.defaults = {
        param: null,
        data: [ {
            text : '甲',
            value : '甲'
        }, {
            text : '乙',
            value : '乙'
        }, {
            text : '丙',
            value : '丙'
        } ],
        valueField: 'value',
        textField: 'text',
        placeholder: '请选择班次'
    };

    //初始化
    $(".selectpicker").each(function () {
        var target = $(this);
        target.attr("title", $.fn.teamSelect.defaults.placeholder);
        target.selectpicker();
    });
})(jQuery);