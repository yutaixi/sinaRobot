(function() {
    var ah = window,
    l = document,
    A = navigator,
    ag = A.userAgent,
    p = ah.screen,
    s;
    try {
        s = ah.location.href
    } catch(au) {
        s = document.URL
    }
    var af = "https:" == ah.location.protocol ? "https://s": "http://",
    v = "beacon.sina.com.cn";
    var q = af + v + "/a.gif?",
    Q = af + v + "/g.gif?",
    z = af + v + "/d.gif?",
    f = af + v + "/e.gif?";
    var c = 0;
    var U = l.referrer.toLowerCase();
    var E = "SINAGLOBAL",
    b = "Apache",
    al = "ULV",
    n = "SUP",
    ao = "UOR",
    ab = "_s_acc",
    S = "_s_tentry";
    var am = 0;
    var y = false;
    var aj = false,
    j = false;
    var ai = "";
    var aa = 16777215,
    I = 0,
    W, d = 0;
    var ak = "",
    H = "",
    G = "";
    var k = [],
    K = [],
    ac = [];
    var aw = 0;
    var Z = (function() {
        var i = window.localStorage,
        D, ax;
        if (i) {
            return {
                get: function(e) {
                    return unescape(i.getItem(e))
                },
                set: function(e, az) {
                    i.setItem(e, escape(az))
                }
            }
        } else {
            if (window.ActiveXObject) {
                D = document.documentElement;
                ax = "localstorage";
                try {
                    D.addBehavior("#default#userdata");
                    D.save("localstorage")
                } catch(ay) {}
                return {
                    set: function(az, aA) {
                        try {
                            D.setAttribute(az, aA);
                            D.save(ax)
                        } catch(aB) {}
                    },
                    get: function(az) {
                        try {
                            D.load(ax);
                            return D.getAttribute(az)
                        } catch(aA) {}
                    }
                }
            } else {
                return {
                    get: h,
                    set: t
                }
            }
        }
    })();
    function Y(ax, i) {
        var D = l.getElementsByName(ax);
        var e = (i > 0) ? i: 0;
        return (D.length > e) ? D[e].content: ""
    }
    function aq(aA, D, ay, ax) {
        if (aA == "") {
            return ""
        }
        ax = (ax == "") ? "=": ax;
        D += ax;
        var az = aA.indexOf(D);
        if (az < 0) {
            return ""
        }
        az += D.length;
        var i = aA.indexOf(ay, az);
        if (i < az) {
            i = aA.length
        }
        return aA.substring(az, i)
    }
    function h(e) {
        if (undefined == e || "" == e) {
            return ""
        }
        return aq(l.cookie, e, ";", "")
    }
    function t(ay, e, i, ax) {
        if (e != null) {
            if ((undefined == ax) || (null == ax)) {
                ax = "weibo.com"
            }
            if ((undefined == i) || (null == i) || ("" == i)) {
                l.cookie = ay + "=" + e + ";domain=" + ax + ";path=/"
            } else {
                var D = new Date();
                var az = D.getTime();
                az = az + 86400000 * i;
                D.setTime(az);
                az = D.getTime();
                l.cookie = ay + "=" + e + ";domain=" + ax + ";expires=" + D.toUTCString() + ";path=/"
            }
        }
    }
    function r(e, ax, D) {
        var i = e;
        if (i == null) {
            return false
        }
        ax = ax || "click";
        if ((typeof D).toLowerCase() != "function") {
            return
        }
        if (i.attachEvent) {
            i.attachEvent("on" + ax, D)
        } else {
            if (i.addEventListener) {
                i.addEventListener(ax, D, false)
            } else {
                i["on" + ax] = D
            }
        }
        return true
    }
    function at() {
        if (window.event != null) {
            return window.event
        } else {
            if (window.event) {
                return window.event
            }
            var D = arguments.callee.caller;
            var i;
            var ax = 0;
            while (D != null && ax < 40) {
                i = D.arguments[0];
                if (i && (i.constructor == Event || i.constructor == MouseEvent || i.constructor == KeyboardEvent)) {
                    return i
                }
                ax++;
                D = D.caller
            }
            return i
        }
    }
    function ar(i) {
        i = i || at();
        if (!i.target) {
            i.target = i.srcElement;
            i.pageX = i.x;
            i.pageY = i.y
        }
        if (typeof i.layerX == "undefined") {
            i.layerX = i.offsetX
        }
        if (typeof i.layerY == "undefined") {
            i.layerY = i.offsetY
        }
        return i
    }
    function a(ax) {
        if (typeof ax !== "string") {
            throw "trim need a string as parameter"
        }
        var e = ax.length;
        var D = 0;
        var i = /(\u3000|\s|\t|\u00A0)/;
        while (D < e) {
            if (!i.test(ax.charAt(D))) {
                break
            }
            D += 1
        }
        while (e > D) {
            if (!i.test(ax.charAt(e - 1))) {
                break
            }
            e -= 1
        }
        return ax.slice(D, e)
    }
    function N(e) {
        return Object.prototype.toString.call(e) === "[object Array]"
    }
    function R(ax, aB) {
        var aD = a(ax).split("&");
        var aC = {};
        var D = function(i) {
            if (aB) {
                return decodeURIComponent(i)
            } else {
                return i
            }
        };
        for (var az = 0,
        aA = aD.length; az < aA; az++) {
            if (aD[az]) {
                var ay = aD[az].split("=");
                var e = ay[0];
                var aE = ay[1];
                if (ay.length < 2) {
                    aE = e;
                    e = "$nullName"
                }
                if (!aC[e]) {
                    aC[e] = D(aE)
                } else {
                    if (N(aC[e]) != true) {
                        aC[e] = [aC[e]]
                    }
                    aC[e].push(D(aE))
                }
            }
        }
        return aC
    }
    function m(D, ay) {
        for (var ax = 0,
        e = D.length; ax < e; ax++) {
            ay(D[ax], ax)
        }
    }
    function T(i) {
        var e = new RegExp("^http(?:s)?://([^/]+)", "im");
        if (i.match(e)) {
            return i.match(e)[1].toString()
        } else {
            return ""
        }
    }
    function X(e, i) {
        var D = "";
        if (e != null && e != "") {
            if (/[?&]sudaref=([\w\.]*)/.test(e)) {
                D = e.match(/[?&]sudaref=([\w\.]*)/);
                if (D.length > 1) {
                    D = D[1]
                }
            }
        }
        if (D == "" && i != null && i != "") {
            D = T(i)
        }
        if (/(\.weibo\.com|\.t\.sina\.com\.cn|\.t\.cn)$/i.test(D) || /^(weibo\.com|t\.sina\.com\.cn|t\.cn)$/i.test(D) || D == "") {
            return ""
        }
        return D
    }
    var x = {
        screenSize: function() {
            return (aa & 8388608 == 8388608) ? p.width + "x" + p.height: ""
        },
        colorDepth: function() {
            return (aa & 4194304 == 4194304) ? p.colorDepth: ""
        },
        appCode: function() {
            return (aa & 2097152 == 2097152) ? A.appCodeName: ""
        },
        appName: function() {
            return (aa & 1048576 == 1048576) ? ((A.appName.indexOf("Microsoft Internet Explorer") > -1) ? "MSIE": A.appName) : ""
        },
        cpu: function() {
            return (aa & 524288 == 524288) ? (A.cpuClass || A.oscpu) : ""
        },
        platform: function() {
            return (aa & 262144 == 262144) ? (A.platform) : ""
        },
        jsVer: function() {
            if (aa & 131072 != 131072) {
                return ""
            }
            var ay, e, aA, D = 1,
            ax = 0,
            i = (A.appName.indexOf("Microsoft Internet Explorer") > -1) ? "MSIE": A.appName,
            az = A.appVersion;
            if ("MSIE" == i) {
                e = "MSIE";
                ay = az.indexOf(e);
                if (ay >= 0) {
                    aA = window.parseInt(az.substring(ay + 5));
                    if (3 <= aA) {
                        D = 1.1;
                        if (4 <= aA) {
                            D = 1.3
                        }
                    }
                }
            } else {
                if (("Netscape" == i) || ("Opera" == i) || ("Mozilla" == i)) {
                    D = 1.3;
                    e = "Netscape6";
                    ay = az.indexOf(e);
                    if (ay >= 0) {
                        D = 1.5
                    }
                }
            }
            return D
        },
        network: function() {
            if (aa & 65536 != 65536) {
                return ""
            }
            var i = "";
            i = (A.connection && A.connection.type) ? A.connection.type: i;
            try {
                l.body.addBehavior("#default#clientCaps");
                i = l.body.connectionType
            } catch(D) {
                i = "unkown"
            }
            return i
        },
        language: function() {
            return (aa & 32768 == 32768) ? (A.systemLanguage || A.language) : ""
        },
        timezone: function() {
            return (aa & 16384 == 16384) ? (new Date().getTimezoneOffset() / 60) : ""
        },
        flashVer: function() {
            if (aa & 8192 != 8192) {
                return ""
            }
            var aA = A.plugins,
            ax, aB, aD;
            if (aA && aA.length) {
                for (var az in aA) {
                    aB = aA[az];
                    if (aB.description == null) {
                        continue
                    }
                    if (ax != null) {
                        break
                    }
                    aD = aB.description.toLowerCase();
                    if (aD.indexOf("flash") != -1) {
                        ax = aB.version ? parseInt(aB.version) : aD.match(/\d+/);
                        continue
                    }
                }
            } else {
                if (window.ActiveXObject) {
                    for (var ay = 10; ay >= 2; ay--) {
                        try {
                            var D = new ActiveXObject("ShockwaveFlash.ShockwaveFlash." + ay);
                            if (D) {
                                ax = ay;
                                break
                            }
                        } catch(aC) {}
                    }
                } else {
                    if (ag.indexOf("webtv/2.5") != -1) {
                        ax = 3
                    } else {
                        if (ag.indexOf("webtv") != -1) {
                            ax = 2
                        }
                    }
                }
            }
            return ax
        },
        javaEnabled: function() {
            if (aa & 4096 != 4096) {
                return ""
            }
            var D = A.plugins,
            i = A.javaEnabled(),
            ax,
            ay;
            if (i == true) {
                return 1
            }
            if (D && D.length) {
                for (var e in D) {
                    ax = D[e];
                    if (ax.description == null) {
                        continue
                    }
                    if (i != null) {
                        break
                    }
                    ay = ax.description.toLowerCase();
                    if (ay.indexOf("java plug-in") != -1) {
                        i = parseInt(ax.version);
                        continue
                    }
                }
            } else {
                if (window.ActiveXObject) {
                    i = (new ActiveXObject("JavaWebStart.IsInstalled") != null)
                }
            }
            return i ? 1 : 0
        }
    };
    var g = {
        pageId: function(i) {
            var D = i || ak,
            aA = "-9999-0-0-1";
            if ((undefined == D) || ("" == D)) {
                try {
                    var ax = Y("publishid");
                    if ("" != ax) {
                        var az = ax.split(",");
                        if (az.length > 0) {
                            if (az.length >= 3) {
                                aA = "-9999-0-" + az[1] + "-" + az[2]
                            }
                            D = az[0]
                        }
                    } else {
                        D = "0"
                    }
                } catch(ay) {
                    D = "0"
                }
                D = D + aA
            }
            return D
        },
        sessionCount: function() {
            var e = h("_s_upa");
            if (e == "") {
                e = 0
            }
            return e
        },
        excuteCount: function() {
            return c
        },
        referrer: function() {
            if (aa & 2048 != 2048) {
                return ""
            }
            var e = /^[^\?&#]*.swf([\?#])?/;
            if ((U == "") || (U.match(e))) {
                var i = aq(s, "ref", "&", "");
                if (i != "") {
                    return escape(i)
                }
            }
            return escape(U)
        },
        isHomepage: function() {
            if (aa & 1024 != 1024) {
                return ""
            }
            var D = "";
            try {
                l.body.addBehavior("#default#homePage");
                D = l.body.isHomePage(s) ? "Y": "N"
            } catch(i) {
                D = "unkown"
            }
            return D
        },
        PGLS: function() {
            return (aa & 512 == 512) ? Y("stencil") : ""
        },
        ZT: function() {
            if (aa & 256 != 256) {
                return ""
            }
            var e = Y("subjectid");
            e.replace(",", ".");
            e.replace(";", ",");
            return escape(e)
        },
        mediaType: function() {
            return (aa & 128 == 128) ? Y("mediaid") : ""
        },
        domCount: function() {
            return (aa & 64 == 64) ? l.getElementsByTagName("*").length: ""
        },
        iframeCount: function() {
            return (aa & 32 == 32) ? l.getElementsByTagName("iframe").length: ""
        },
        onloadTime: function() {
            var e;
            if (typeof __GLOBAL_STATS_PAGESTART_TIME__ != "undefined" && typeof __GLOBAL_STATS_PAGEONLOAD_TIME__ != "undefined") {
                e = __GLOBAL_STATS_PAGEONLOAD_TIME__ - __GLOBAL_STATS_PAGESTART_TIME__
            }
            return (aa & 16 == 16) ? e || "": ""
        },
        domReadyTime: function() {
            var e;
            if (typeof __GLOBAL_STATS_PAGESTART_TIME__ != "undefined" && typeof __GLOBAL_STATS_DOMREADY_TIME__ != "undefined") {
                e = __GLOBAL_STATS_DOMREADY_TIME__ - __GLOBAL_STATS_PAGESTART_TIME__
            }
            return (aa & 8 == 8) ? e || "": ""
        },
        bigpipe: function() {
            return (typeof $CONFIG != "undefined" && typeof $CONFIG.bigpipe != "undefined" && $CONFIG.bigpipe == "true") ? 1 : 0
        },
        getRealURL: function() {
            var e = "";
            if (typeof STK != "undefined" && typeof STK.historyM != "undefined" && typeof STK.historyM.parseURL != "undefined") {
                e = escape(STK.historyM.parseURL().url)
            }
            return e
        }
    };
    var P = {
        visitorId: function() {
            if ("" != E) {
                var e = h(E);
                if ("" == e) {
                    e = h(b);
                    t(E, e, 3650)
                }
                return e
            } else {
                return ""
            }
        },
        sessionId: function() {
            var e = h(b);
            if ("" == e) {
                var i = new Date();
                e = Math.random() * 10000000000000 + "." + i.getTime();
                t(b, e)
            }
            return e
        },
        lastVisit: function() {
            var D = h(b);
            var ay = h(al);
            var ax = ay.split(":");
            var az = "",
            i;
            if (ax.length >= 6) {
                if (D != ax[4]) {
                    i = new Date();
                    var e = new Date(window.parseInt(ax[0]));
                    ax[1] = window.parseInt(ax[1]) + 1;
                    if (i.getMonth() != e.getMonth()) {
                        ax[2] = 1
                    } else {
                        ax[2] = window.parseInt(ax[2]) + 1
                    }
                    if (((i.getTime() - e.getTime()) / 86400000) >= 7) {
                        ax[3] = 1
                    } else {
                        if (i.getDay() < e.getDay()) {
                            ax[3] = 1
                        } else {
                            ax[3] = window.parseInt(ax[3]) + 1
                        }
                    }
                    az = ax[0] + ":" + ax[1] + ":" + ax[2] + ":" + ax[3];
                    ax[5] = ax[0];
                    ax[0] = i.getTime();
                    t(al, ax[0] + ":" + ax[1] + ":" + ax[2] + ":" + ax[3] + ":" + D + ":" + ax[5], 360)
                } else {
                    az = ax[5] + ":" + ax[1] + ":" + ax[2] + ":" + ax[3]
                }
            } else {
                i = new Date();
                az = ":1:1:1";
                t(al, i.getTime() + az + ":" + D + ":", 360)
            }
            return az
        },
        userNick: function() {
            if (ai != "") {
                return ai
            }
            var D = unescape(h(n));
            if (D != "") {
                var i = aq(D, "ag", "&", "");
                var e = aq(D, "user", "&", "");
                var ax = aq(D, "uid", "&", "");
                var az = aq(D, "sex", "&", "");
                var ay = aq(D, "dob", "&", "");
                ai = i + ":" + e + ":" + ax + ":" + az + ":" + ay;
                return ai
            } else {
                return ""
            }
        },
        userOrigin: function() {
            if (aa & 4 != 4) {
                return ""
            }
            var e = h(ao);
            var i = e.split(":");
            return i[0]
        },
        subp: function() {
            return h("SUBP")
        },
        advCount: function() {
            return (aa & 2 == 2) ? h(ab) : ""
        },
        setUOR: function() {
            var ay = h(ao),
            aC = "",
            e = "",
            aB = "",
            D = "",
            az = s.toLowerCase(),
            i = l.referrer.toLowerCase();
            var aD = /[&|?]c=spr(_[A-Za-z0-9]{1,}){3,}/;
            if (az.match(aD)) {
                aB = az.match(aD)[0]
            } else {
                if (i.match(aD)) {
                    aB = i.match(aD)[0]
                }
            }
            if (aB != "") {
                aB = aB.substr(3)
            } else {
                aB = X(az, i)
            }
            if (ay == "" && aB != "") {
                if (h(al) == "") {
                    aC = T(i);
                    e = T(az)
                }
                t(ao, aC + "," + e + "," + aB, 365)
            } else {
                var ax = 0,
                aA = ay.split(",");
                if (aA.length >= 1) {
                    aC = aA[0]
                }
                if (aA.length >= 2) {
                    e = aA[1]
                }
                if (aA.length >= 3) {
                    D = aA[2]
                }
                if (aB != "") {
                    ax = 1
                }
                if (ax) {
                    t(ao, aC + "," + e + "," + aB, 365)
                }
            }
        },
        setAEC: function(e) {
            if ("" == e) {
                return
            }
            var i = h(ab);
            if (i.indexOf(e + ",") < 0) {
                i = i + e + ","
            }
            t(ab, i, 7)
        }
    };
    var av = {
        picList: [{
            url: "http://ww1.sinaimg.cn/large/53d96fe3tw1diw52tyd28j.jpg",
            size: 23243
        },
        {
            url: "http://ww2.sinaimg.cn/large/53d96fe3tw1diw52tyd28j.jpg",
            size: 23243
        },
        {
            url: "http://ww3.sinaimg.cn/large/53d96fe3tw1diw52tyd28j.jpg",
            size: 23243
        },
        {
            url: "http://ww4.sinaimg.cn/large/53d96fe3tw1diw52tyd28j.jpg",
            size: 23243
        }],
        picOk: false,
        picData: [],
        picture: function() {
            if ((I & 8) != 8) {
                av.picOk = true;
                return ""
            }
            var ax = new Date().getTime();
            var az = [],
            D;
            for (var ay = 0,
            e = av.picList.length; ay < e; ay++) {
                D = new Image();
                SUDA.img = D;
                D.src = av.picList[ay].url + "?" + Math.random();
                D.onload = (function(i) {
                    return function() {
                        var aA = new Date().getTime();
                        av.picData[i] = Math.floor((av.picList[i].size / 1024) / ((aA - ax) / 1000));
                        if (/^(\d+,){3}\d+$/.test(av.picData.join(","))) {
                            av.picOk = true;
                            J()
                        }
                    }
                })(ay);
                az.push(D)
            }
        },
        porList: [{
            url: "http://tp1.sinaimg.cn/1772026304/50/5603504743/1",
            size: 3488
        },
        {
            url: "http://tp2.sinaimg.cn/1780417033/50/1280367872/0",
            size: 4021
        },
        {
            url: "http://tp3.sinaimg.cn/1075913170/50/5601477177/1",
            size: 3456
        },
        {
            url: "http://tp4.sinaimg.cn/1245851035/50/1279876078/1",
            size: 2519
        }],
        porOk: false,
        porData: [],
        portait: function() {
            if ((I & 4) != 4) {
                av.porOk = true;
                return ""
            }
            var ax = new Date().getTime();
            var az = [],
            D;
            for (var ay = 0,
            e = av.porList.length; ay < e; ay++) {
                D = new Image();
                SUDA.img = D;
                D.src = av.porList[ay].url + "?" + Math.random();
                D.onload = (function(i) {
                    return function() {
                        var aA = new Date().getTime();
                        av.porData[i] = Math.floor((av.porList[i].size / 1024) / ((aA - ax) / 1000));
                        if (/^(\d+,){3}\d+$/.test(av.porData.join(","))) {
                            av.porOk = true;
                            J()
                        }
                    }
                })(ay);
                az.push(D)
            }
        },
        jsList: [{
            url: "http://js.t.sinajs.cn/t4/home/static/suda/feed.png",
            size: 21287
        }],
        jsOk: false,
        jsData: [],
        jsSpeed: function() {
            if ((I & 2) != 2) {
                av.jsOk = true;
                return ""
            }
            var ax = new Date().getTime();
            var az = [],
            D;
            for (var ay = 0,
            e = av.jsList.length; ay < e; ay++) {
                D = new Image();
                SUDA.img = D;
                D.src = av.jsList[ay].url + "?" + Math.random();
                D.onload = (function(i) {
                    return function() {
                        var aA = new Date().getTime();
                        av.jsData[i] = Math.floor((av.jsList[i].size / 1024) / ((aA - ax) / 1000));
                        if (/^\d+$/.test(av.jsData.join(","))) {
                            av.jsOk = true;
                            J()
                        }
                    }
                })(ay);
                az.push(D)
            }
        },
        cssList: [{
            url: "http://img.t.sinajs.cn/t4/style/images/mobile/android/cp_3.jpg",
            size: 24472
        }],
        cssOk: false,
        cssData: [],
        cssSpeed: function() {
            if ((I & 1) != 1) {
                av.cssOk = true;
                return ""
            }
            var ax = new Date().getTime();
            var az = [],
            D;
            for (var ay = 0,
            e = av.cssList.length; ay < e; ay++) {
                D = new Image();
                SUDA.img = D;
                D.src = av.cssList[ay].url + "?" + Math.random();
                D.onload = (function(i) {
                    return function() {
                        var aA = new Date().getTime();
                        av.cssData[i] = Math.floor((av.cssList[i].size / 1024) / ((aA - ax) / 1000));
                        if (/^\d+$/.test(av.cssData.join(","))) {
                            av.cssOk = true;
                            J()
                        }
                    }
                })(ay);
                az.push(D)
            }
        }
    };
    var u = {
        CI: function() {
            var e = ["sz:" + x.screenSize(), "dp:" + x.colorDepth(), "ac:" + x.appCode(), "an:" + x.appName(), "cpu:" + x.cpu(), "pf:" + x.platform(), "jv:" + x.jsVer(), "ct:" + x.network(), "lg:" + x.language(), "tz:" + x.timezone(), "fv:" + x.flashVer(), "ja:" + x.javaEnabled()];
            return "CI=" + e.join("|")
        },
        PI: function(e) {
            var i = ["pid:" + g.pageId(e), "st:" + g.sessionCount(), "et:" + g.excuteCount(), "ref:" + g.referrer(), "hp:" + g.isHomepage(), "PGLS:" + g.PGLS(), "ZT:" + g.ZT(), "MT:" + g.mediaType(), "keys:", "dom:" + g.domCount(), "ifr:" + g.iframeCount(), "nld:" + g.onloadTime(), "drd:" + g.domReadyTime(), "bp:" + g.bigpipe(), "url:" + g.getRealURL()];
            return "PI=" + i.join("|")
        },
        UI: function() {
            var e = ["vid:" + P.visitorId(), "sid:" + P.sessionId(), "lv:" + P.lastVisit(), "un:" + P.userNick(), "uo:" + P.userOrigin(), "ae:" + P.advCount(), "su:" + P.subp()];
            return "UI=" + e.join("|")
        },
        EX: function(i, e) {
            if (aa & 1 != 1) {
                return ""
            }
            i = (null != i) ? i || "": H;
            e = (null != e) ? e || "": G;
            return "EX=ex1:" + i + "|ex2:" + e
        },
        EXT: function(i, e) {
            if (aa & 1 != 1) {
                return ""
            }
            i = (null != i) ? i || "": H;
            e = (null != e) ? e || "": G;
            return i + ":" + e
        },
        P_PI: function() {
            var e = ["pic:" + av.picture(), "por:" + av.portait(), "js:" + av.jsSpeed(), "css:" + av.cssSpeed(), "ref:" + g.referrer()];
            return "PI=" + e.join("|")
        },
        P_UI: function() {
            var e = ["vid:" + P.visitorId(), "sid:" + P.sessionId(), "un:" + P.userNick()];
            return "UI=" + e.join("|")
        },
        V: function() {
            return "V=2.2.3"
        },
        R: function() {
            return "gUid_" + new Date().getTime()
        }
    };
    function M() {
        var aA = "-",
        ax = l.referrer.toLowerCase(),
        D = s.toLowerCase();
        if ("" == h(S)) {
            if ("" != ax) {
                aA = T(ax)
            }
            t(S, aA, "", "weibo.com")
        }
        var ay = /weibo.com\/(reg\.php|signup\/(signup|mobile|full_info)\.php)/;
        if (D.match(ay)) {
            var az = aq(unescape(D), "sharehost", "&", "");
            var i = aq(unescape(D), "appkey", "&", "");
            if ("" != az) {
                t(S, az, "", "weibo.com")
            }
            t("appkey", i, "", "weibo.com")
        }
    }
    function ae(e, i) {
        C(e, i)
    }
    function C(i, D) {
        D = D || {};
        var e = new Image(),
        ax;
        if (D && D.callback && typeof D.callback == "function") {
            e.onload = function() {
                clearTimeout(ax);
                ax = null;
                D.callback(true)
            }
        }
        SUDA.img = e;
        e.src = i;
        ax = setTimeout(function() {
            if (D && D.callback && typeof D.callback == "function") {
                D.callback(false);
                e.onload = null
            }
        },
        D.timeout || 2000)
    }
    function ad() {
        if (aj || j) {
            return
        }
        if (aw < 3 && typeof __GLOBAL_STATS_PAGESTART_TIME__ != "undefined") {
            aw++;
            if (typeof __GLOBAL_STATS_PAGESTART_TIME__ == "undefined" || typeof __GLOBAL_STATS_DOMREADY_TIME__ == "undefined" || typeof __GLOBAL_STATS_PAGEONLOAD_TIME__ == "undefined") {
                setTimeout(ad, 1000);
                return
            }
        }
        P.sessionId();
        w()
    }
    function w(e, ax, D) {
        c++;
        var ay = P.visitorId();
        if (am < 1) {
            setTimeout(ad, 0);
            am++;
            return
        } else {}
        var i = q + [u.V(), u.CI(), u.PI(e), u.UI(), u.EX(ax, D), u.R()].join("&");
        C(i);
        an()
    }
    function an() {
        if (typeof __GLOBAL_STATS_PAGESTART_TIME__ != "undefined") {
            if (typeof __GLOBAL_STATS_PAGESTART_TIME__ == "undefined" || typeof __GLOBAL_STATS_DOMREADY_TIME__ == "undefined" || typeof __GLOBAL_STATS_PAGEONLOAD_TIME__ == "undefined") {
                setTimeout(an, 1000);
                return
            }
        }
        if ("https:" == l.location.protocol || y == true) {
            return
        }
        if (typeof W == "function") {
            if (W() == false) {
                return
            }
        }
        var e = Z.get("sudaPerformance");
        if (e != null && (new Date().getTime() - e) < d * 60 * 1000) {
            return
        }
        if (document.readyState != "complete") {
            setTimeout(an, 500)
        }
        c++;
        y = true;
        if (I > 0) {
            av.picture();
            av.portait();
            av.jsSpeed();
            av.cssSpeed()
        }
    }
    function J() {
        if (I > 0 && av.picOk && av.porOk && av.jsOk && av.cssOk) {
            var e = Q + [u.V(), "PI=pic:" + av.picData + "|por:" + av.porData + "|js:" + av.jsData + "|css:" + av.cssData + "|nld:" + g.onloadTime() + "|drd:" + g.domReadyTime(), u.P_UI(), u.EX(), u.R()].join("&");
            C(e)
        }
        Z.set("sudaPerformance", new Date().getTime())
    }
    function L(e, ax) {
        if (("" == e) || (undefined == e)) {
            return
        }
        P.setAEC(e);
        if (0 == ax) {
            return
        }
        var D = "AcTrack||" + h(E) + "||" + h(b) + "||" + P.userNick() + "||" + e + "||";
        var i = f + D + u.EXT() + "&gUid_" + new Date().getTime();
        ae(i)
    }
    function O(aA, aB, e) {
        e = e || {};
        var aD = "UATrack||" + h(E) + "||" + h(b) + "||" + P.userNick() + "||" + aA + "||" + aB + "||" + g.referrer() + "||" + (e.href || "") + "||" + (e.realUrl || "") + "||";
        var D = f + aD + u.EXT() + "&gUid_" + new Date().getTime();
        ae(D, e);
        if (e.urls) {
            if (e.urls.replace(/\s/g, "") != "") {
                var az = e.urls.split(",");
                if (az.length > 0) {
                    for (var ax = 0,
                    ay = az.length; ax < ay; ax++) {
                        var aC = az[ax] + (az[ax].indexOf("?") == -1 ? "?": "&") + aD + "&gUid_" + new Date().getTime();
                        ae(aC, e)
                    }
                }
            }
        }
    }
    function F(aB) {
        var aE = ar(aB);
        var D = aE.target;
        var az = "",
        aD = "",
        aC = "",
        ay = "",
        i = "",
        aA = "";
        var ax;
        if (D != null && D.getAttribute && (!D.getAttribute("suda-uatrack") && !D.getAttribute("suda-actrack") && !D.getAttribute("suda-data"))) {
            while (D != null && D.getAttribute && ( !! D.getAttribute("suda-uatrack") || !!D.getAttribute("suda-actrack") || !!D.getAttribute("suda-data")) == false) {
                if (D == l.body) {
                    return
                }
                D = D.parentNode
            }
        }
        if (D == null || D.getAttribute == null) {
            return
        }
        az = D.getAttribute("suda-actrack") || "";
        aD = D.getAttribute("suda-uatrack") || D.getAttribute("suda-data") || "";
        ay = D.getAttribute("suda-urls") || "";
        aC = o(D);
        if (aD) {
            ax = R(aD);
            if (D.tagName.toLowerCase() == "a") {
                i = escape(D.href)
            }
            aA = g.getRealURL();
            ax.value = ax.value + aC;
            ax.key && SUDA.uaTrack && SUDA.uaTrack(ax.key, ax.value || ax.key, {
                href: i,
                realUrl: aA,
                urls: ay
            })
        }
        if (az) {
            ax = R(az);
            ax.key && SUDA.acTrack && SUDA.acTrack(ax.key, ax.value || ax.key)
        }
    }
    function o(i) {
        var D = i.getAttribute("suda-sizzle");
        var e = "";
        if (D == null || D == "") {
            return ""
        }
        e = V(i, D);
        return e
    }
    function V(D, aF) {
        var aL;
        if (/^[-\d]/.test(aF)) {
            var aJ = aF.split(",");
            var aE = aJ[0];
            var e = aJ[1];
            var aG = aJ[2];
            aL = aJ[3] || "*";
            var aI, aB;
            if (aE == null || e == null || aG == null) {
                return ""
            }
            switch (aG) {
            case "parent":
                D = D.parentNode;
                while (D != null) {
                    if (D.getAttribute("suda-sizzle") != null) {
                        if (aL != "*" && D.tagName.toLowerCase() != aL) {
                            D = D.parentNode;
                            if (D == document.body) {
                                return ""
                            }
                            continue
                        }
                        aI = D.getAttribute("suda-sizzle");
                        aB = aI.split(",");
                        if (aI[0] && e == aI[0]) {
                            return (D.getAttribute("suda-ext") || "") + V(D, aI)
                        } else {
                            D = D.parentNode;
                            if (D == document.body) {
                                return ""
                            }
                            continue
                        }
                    }
                    D = D.parentNode;
                    if (D == document.body) {
                        return ""
                    }
                }
                return "";
            case "sibling":
                D = D.parentNode.children || D.parentNode.childNodes;
                for (var ay = 0,
                aC = D.length; ay < aC; ay++) {
                    if (D[ay].getAttribute("suda-sizzle") != null) {
                        if (aL != "*" && D[ay].tagName.toLowerCase() != aL) {
                            continue
                        }
                        aI = D[ay].getAttribute("suda-sizzle");
                        aB = aI.split(",");
                        if (aI[0] && e == aI[0]) {
                            return (D[ay].getAttribute("suda-ext") || "") + V(D[ay], aI)
                        } else {
                            continue
                        }
                    }
                }
                return "";
            case "child":
                D = D.getElementsByTagName(aL);
                for (var ax = 0,
                aA = D.length; ax < aA; ax++) {
                    if (D[ax].getAttribute("suda-sizzle") != null) {
                        if (aL != "*" && D[ax].tagName.toLowerCase() != aL) {
                            continue
                        }
                        aI = D[ax].getAttribute("suda-sizzle");
                        aB = aI.split(",");
                        if (aI[0] && e == aI[0]) {
                            return (D[ax].getAttribute("suda-ext") || "") + V(D[ax], aI)
                        } else {
                            continue
                        }
                    }
                }
                return ""
            }
        } else {
            var aK = aF.split("["),
            aH;
            switch (aK.length) {
            case 1:
                aL = aK[0];
                break;
            case 2:
            default:
                aL = aK[0];
                aH = aK[1];
                break
            }
            if (aH) {
                aH = aH.replace(/['"\]]/g, "").split("=");
                if (aH.length == 2) {
                    var aD = aH[0];
                    var az = aH[1]
                }
            }
            D = D.parentNode;
            while (D != null) {
                if (D.tagName.toLowerCase() == aL) {
                    if (aD && D.getAttribute(aD) == az) {
                        if (D.getAttribute("suda-sizzle") != null && D.getAttribute("suda-sizzle") != "") {
                            return (D.getAttribute("suda-ext") || "") + V(D, D.getAttribute("suda-sizzle"))
                        }
                        return (D.getAttribute("suda-ext") || "")
                    }
                    if (aD == null) {
                        if (D.getAttribute("suda-sizzle") != null && D.getAttribute("suda-sizzle") != "") {
                            return (D.getAttribute("suda-ext") || "") + V(D, D.getAttribute("suda-sizzle"))
                        }
                        return (D.getAttribute("suda-ext") || "")
                    }
                }
                D = D.parentNode;
                if (D == document.body) {
                    return ""
                }
            }
            return ""
        }
    }
    if (window.SUDA && Object.prototype.toString.call(window.SUDA) === "[object Array]") {
        for (var ap = 0,
        B = SUDA.length; ap < B; ap++) {
            switch (SUDA[ap][0]) {
            case "setGatherType":
                aa = SUDA[ap][1];
                break;
            case "setGatherInfo":
                ak = SUDA[ap][1] || ak;
                H = SUDA[ap][2] || H;
                G = SUDA[ap][3] || G;
                break;
            case "setPerformance":
                I = SUDA[ap][1];
                break;
            case "setPerformanceFilter":
                W = SUDA[ap][1];
                break;
            case "setPerformanceInterval":
                d = SUDA[ap][1] * 1 || 0;
                d = isNaN(d) ? 0 : d;
                break;
            case "setGatherMore":
                k.push(SUDA[ap].slice(1));
                break;
            case "acTrack":
                K.push(SUDA[ap].slice(1));
                break;
            case "uaTrack":
                ac.push(SUDA[ap].slice(1));
                break
            }
        }
    }
    M();
    P.setUOR();
    aj = (function(D, i) {
        if (ah.top == ah) {
            return false
        } else {
            try {
                if (l.body.clientHeight == 0) {
                    return false
                }
                return ((l.body.clientHeight >= D) && (l.body.clientWidth >= i)) ? false: true
            } catch(ax) {
                return true
            }
        }
    })(320, 240);
    j = (function() {
        return false
    })();
    ad();
    if (typeof $CONFIG != "undefined" && typeof $CONFIG.bigpipe != "undefined" && $CONFIG.bigpipe === "true" && typeof STK != "undefined" && typeof STK.historyM != "undefined" && typeof STK.historyM.onpopstate != "undefined") {
        STK.historyM.onpopstate(function(e, i) {
            if (i == true) {
                ad()
            }
        })
    }
    if (k.length > 0) {
        m(k,
        function(i, e) {
            w.apply(null, i)
        })
    }
    if (K.length > 0) {
        m(K,
        function(i, e) {
            L.apply(null, i)
        })
    }
    if (ac.length > 0) {
        m(ac,
        function(i, e) {
            O.apply(null, i)
        })
    }
    window.SUDA = window.SUDA || [];
    SUDA.log = function() {
        w.apply(null, arguments)
    };
    SUDA.acTrack = function() {
        L.apply(null, arguments)
    };
    SUDA.uaTrack = function() {
        O.apply(null, arguments)
    };
    r(l.body, "click", F)
})();