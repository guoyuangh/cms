/* =========================================================
 * bootstrap-validation.js 
 * Original Idea: http:/www.newkou.org (Copyright 2012 Stefan Petre)
 * Updated by 不会飞的羊 (https://github.com/FateSheep/Validation-for-Bootstrap)
 * =========================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================= */
!function($) {
    $.fn.validation = function(options) {
        return this.each(function() {
            globalOptions = $.extend({}, $.fn.validation.defaults, options);
            validationForm(this)
        });
    };
    
    $.fn.validationAjax = function(options) {
    	globalOptions = $.extend({}, $.fn.validation.defaults, options);
        return validationFormAjax(this);
    };
    
    $.fn.validation.defaults = {
        validRules : [
            {name: 'required', validate: function(value) {return ($.trim(value) == '');}, defaultMsg: '请输入内容。'},
            {name: 'maxLength', validate: function(value) {
                return (value.replace(/[^\u0000-\u00ff]/g,"00").length > this.attributes['max-length'].value);
            }, defaultMsg: '文本长度超出指定长度!'},
            {name: 'number', validate: function(value) {if(value!="") return (!/^[0-9]\d*$/.test(value));}, defaultMsg: '请输入数字。'},
            {name: 'doublenumber', validate: function(value) {if(value!="") return (!/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/.test(value));}, defaultMsg: '请输入货币数字或者浮动数字。'},
            {name: 'mail', validate: function(value) {return (!/^[a-zA-Z0-9]{1}([\._a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+){1,3}$/.test(value));}, defaultMsg: '请输入邮箱地址。'},
            {name: 'char', validate: function(value) {return (!/^[a-z\_\-A-Z]*$/.test(value));}, defaultMsg: '请输入英文字符。'},
            {name: 'chinese', validate: function(value) {return (!/^[\u4e00-\u9fff]$/.test(value));}, defaultMsg: '请输入汉字。'},
            {name: 'ajaxCheck', validate: function(value) {
            				//调用ajaxFunction属性设置的函数名
            				return window[this.attributes['ajaxFunction'].value](value);
            			}, defaultMsg: '请输入内容。'
            		},
            
            {name: 'checkConfirm', validate: function(value) {return checkConfirm(value);}, defaultMsg: '确认密码不能为空!'},
            {name: 'checkPassword', validate: function(value) {return checkPassword(value);}, defaultMsg: '密码不一致!'},
            {name: 'checkNew', validate: function(value) {return checkNew(value);}, defaultMsg: '与原密码不一致!'},
            {name: 'officePhone', validate: function(value) {if(value!="")return (!/^(\d{3,4}-)?\d{7,8}$/.test(value));}, defaultMsg: '电话号码格式不对!'},
            {name: 'mobilePhone', validate: function(value) {if(value!="")return (!/^1[35][0-9]{9}$/.test(value));}, defaultMsg: '手机号码格式不对!'},
            {name: 'checkZero', validate: function(value) {return (/^[0]/.test(value));}, defaultMsg: '不能以数字0开头！'},
            {name: 'checkIp', validate: function(value) {return (!/^((25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)\.){3}(25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)$/.test(value));}, defaultMsg: 'ip地址不合法！'},
            {name: 'checkLoginName', validate: function(value) {return checkLoginName(value); }, defaultMsg: '已存在相同的用户登陆名！'},
            {name: 'checkUniqueKey', validate: function(value) {return checkUniqueKey(value); }, defaultMsg: '已存在名称！请修改！'},
            {name: 'checkMultipleIp', validate: function(value) {return (!/^(([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3},?)+$/.test(value));}, defaultMsg: 'ip地址不合法！'},
            {name: 'checkIp2', validate: function(value) {return (!/^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/.test(value));}, defaultMsg: 'ip地址不合法！'},
            {name:'checkAnNcIp',validate: function(value) {
            	//A设备Loopback地址及互联地址只能是3.X.X.X/24或4.X.X.X/24
            	if(!/^(3|4)\.((25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)\.){2}(25[0-5]|2[0-4]\d|(1\d|[1-9])?\d)\/24$/.test(value)){return true;}else {
					return false;
				}
            }, defaultMsg: 'IP地址段不符合业务规则!'},
        ]
    };

    var formState = false, fieldState = false, wFocus = false, globalOptions = {};
    
    var validateField = function(field, valid) { // 验证字段
        var el = $(field), error = false, errorMsg = '';
        for (i = 0; i < valid.length; i++) {
            var x = true, flag = valid[i], msg = (el.attr(flag + '-message')==undefined)?null:el.attr(flag + '-message');;
            if (flag.substr(0, 1) == '!') {
                x = false;
                flag = flag.substr(1, flag.length - 1);
            }

            var rules = globalOptions.validRules;
            for (j = 0; j < rules.length; j++) {
                var rule = rules[j];
                if (flag == rule.name) {
                    if (rule.validate.call(field, el.val()) == x) {
                        error = true;
                        errorMsg = (msg == null)?rule.defaultMsg:msg;
                        break;
                    }
                }
            }

            if (error) {break;}
        }

        var controls = el.parents('.controls'), controlGroup = el.parents('.control-group'), errorEl = controls.children('.help-block, .help-inline');

        if (error) {
            if (!controlGroup.hasClass('error')) {
                if (errorEl.length > 0) {
                    var help = errorEl.text();
                    controls.data('help-message', help);
                    errorEl.text(errorMsg);
                } else {
                    controls.append('<span class="help-inline">'+errorMsg+'</span>');
                }
                controlGroup.addClass('error');
            }
        } else {
            if (fieldState) {
                if (errorEl.length > 0) {
                    var help = controls.data('help-message');
                    if (help == undefined) {
                        errorEl.remove();
                    } else {
                        errorEl.text(help);
                    }
                }
                controlGroup.attr('class','control-group');
            } else {
                if (errorEl.length > 0) {
                    var help = errorEl.text();
                    controls.data('help-message', help);
                }
            }
        }
        return !error;
    };

    var validationForm = function(obj) { // 表单验证方法
        $(obj).submit(function() { // 提交时验证
            if (formState) { // 重复提交则返回
                return false;
            }
            formState = true;
            var validationError = false;
            $('input, textarea', this).each(function () {
                var el = $(this), valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
                if (valid != null && valid.length > 0) {
                    if (!validateField(this, valid)) {
                        if (wFocus == false) {
                            scrollTo(0, el[0].offsetTop - 50);
                            wFocus = true;
                        }

                        validationError = true;
                    }
                }
            });

            wFocus = false;
            fieldState = true;

            if (validationError) {
                formState = false; 

                $('input, textarea').each(function() {
                    var el = $(this), valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
                    if (valid != null && valid.length > 0) {
                        el.focus(function() { // 获取焦点时
                            var controls = el.parents('.controls'), controlGroup = el.parents('.control-group'), errorEl = controls.children('.help-block, .help-inline');
                            if (errorEl.length > 0) {
                                var help = controls.data('help-message');
                            
                                if (help == undefined) {
                                    errorEl.remove();
                                } else {
                                    errorEl.text(help);
                                }
                            }
                            controlGroup.attr('class','control-group');
                        });

                        el.blur(function() { // 失去焦点时
                            validateField(this, valid);
                        });
                    }
                });
                
                return false;
            }

            return true;
        });


    };
    
    var validationFormAjax = function(obj) { // 表单验证方法
        var validationError = false;
        $('input, textarea, select', obj).each(function () {
            var el = $(this), valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
            if (valid != null && valid.length > 0) {
                if (!validateField(this, valid)) {
                    if (wFocus == false) {
                        scrollTo(0, el[0].offsetTop - 50);
                        wFocus = true;
                    }

                    validationError = true;
                }
            }
        });

        wFocus = false;
        fieldState = true;

        if (validationError) {

            $('input, textarea, select', obj).each(function() {
                var el = $(this), valid = (el.attr('check-type')==undefined)?null:el.attr('check-type').split(' ');
                if (valid != null && valid.length > 0) {
                    el.focus(function() { // 获取焦点时
                        var controls = el.parents('.controls'), controlGroup = el.parents('.control-group'), errorEl = controls.children('.help-block, .help-inline');
                        if (errorEl.length > 0) {
                            var help = controls.data('help-message');
                            if (help == undefined) {
                                errorEl.remove();
                            } else {
                                errorEl.text(help);
                            }
                        }
                        controlGroup.attr('class','control-group');
                    });

                    el.blur(function() { // 失去焦点时
                        validateField(this, valid);
                    });
                }
            });
            
            return false;
        }

        return true;

    };
}(window.jQuery);