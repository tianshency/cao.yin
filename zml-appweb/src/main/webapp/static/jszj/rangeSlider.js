 $.fn.RangeSlider = function (cfg) {
     this.sliderCfg = {
         min: cfg && !isNaN(parseFloat(cfg.min)) ? Number(cfg.min) : null
         , max: cfg && !isNaN(parseFloat(cfg.max)) ? Number(cfg.max) : null
         , step: cfg && Number(cfg.step) ? cfg.step : 1
         , callback: cfg && cfg.callback ? cfg.callback : null
     };
     var $input = $(this);
     var min = this.sliderCfg.min;
     var max = this.sliderCfg.max;
     var step = this.sliderCfg.step;
     var callback = this.sliderCfg.callback;
     $input.attr('min', min).attr('max', max).attr('step', step);
     $input.bind("input", function (e) {
         $input.attr('value', this.value);
         //console.log(this.value);
         $('.my_account').html(this.value);
         var m = parseInt($('.choice').attr('data-value'));
         var money = this.value;
         var per = parseInt(money * 0.003 + money / m);
         $('.bantopr').html(per);
         var percent = this.value / max * 100;
         $input.css('background', 'linear-gradient(to right,#FEF059, #E0E5F0 ' + percent + '%,#E0E5F0)');
         if ($.isFunction(callback)) {
             callback(this);
         }
     });
 };
 $(function () {
     $('#loan_amount').RangeSlider({
         min: 1000
         , max: 50000
         , step: 1000
     });
 })