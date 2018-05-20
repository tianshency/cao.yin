$.ajaxSetup({
    beforeSend: function (xhr) {
        var $token = ''
            , url = $.trim(this.url)
            , type = this.type.toUpperCase();
        for (var i = -1, arr = [];
            (i = url.indexOf("?", i + 1)) > -1; arr.push(i));
        if (type == 'GET') {
            if (arr != '') {
                var dataString = url.substring(arr).replace('?', '');
                var url = url.substring(0, arr);
            }
            else {
                var dataString = "";
            }
        }
        else {
            var dataString = this.data;
        }
        var $signUrl = hex_sha1(url)
            , $dataString = dataString.replace(/=/g, '').split('&').sort().join('')
            , $startString = $dataString.substring(0, 3)
            , $endString = $dataString.substring($dataString.length - 3)
            , $sha1Text = $startString + $token + $endString + $signUrl
            , $sha1Sign = hex_sha1($sha1Text);
        xhr.setRequestHeader("X-Sign", $sha1Sign);
        xhr.setRequestHeader("X-Token", $token);
    }
    , error: function (error) {
        console.log(error)
    }
});