package com.qunar.qfproxy.model;

import java.util.List;

public class EmoPackXML {

    /**
     * FACESETTING : {"DEFAULTFACE":{"-emotionName":"Star_hegemony","-version":"1000","-count":"17","-showall":"0","-line":"5","-Width":"24","-Height":"24","-package":"Star_hegemony","FACE":[{"-id":"cdd7494956dd0defd182a4244b19d83f","-shortcut":"/ok","-tip":"OK","-multiframe":"4","FILE_ORG":"OK.png","FILE_FIXED":"OK.png"},{"-id":"187f03e1caa406a5fc6cf55c8bd135d6","-shortcut":"/sayhi","-tip":"say hi","-multiframe":"3","FILE_ORG":"say-hi.png","FILE_FIXED":"say-hi.png"},{"-id":"0df4c667df602869c0741f1517fb0ddc","-shortcut":"/heng","-tip":"heng","-multiframe":"5","FILE_ORG":"heng.png","FILE_FIXED":"heng.png"},{"-id":"f79f75cf746ee63bbb759f902b7de7cb","-shortcut":"/09","-tip":"委屈","-multiframe":"4","FILE_ORG":"09.png","FILE_FIXED":"09.png"},{"-id":"69e440d0bd443894c194b9fe8c1cd103","-shortcut":"/11","-tip":"心塞","-multiframe":"10","FILE_ORG":"11.png","FILE_FIXED":"11.png"},{"-id":"ecf494d8ac668246672ec06dacafa2d3","-shortcut":"/12","-tip":"星星","-multiframe":"24","FILE_ORG":"12.png","FILE_FIXED":"12.png"},{"-id":"015df3e1c164f6afd302d7d912ae4d10","-shortcut":"/08","-tip":"期待","-multiframe":"7","FILE_ORG":"08.png","FILE_FIXED":"08.png"},{"-id":"47c6d627e4161e3c0b8383ea4dc64ccb","-shortcut":"/01","-tip":"白眼","-multiframe":"2","FILE_ORG":"01.png","FILE_FIXED":"01.png"},{"-id":"275bc2321f1cbcd9d13e4146a835b261","-shortcut":"/14","-tip":"种草","-multiframe":"6","FILE_ORG":"14.png","FILE_FIXED":"14.png"},{"-id":"30ea70dd93f86a8d2d136b5431f0c9f2","-shortcut":"/07","-tip":"叫霸霸","-multiframe":"2","FILE_ORG":"07.png","FILE_FIXED":"07.png"},{"-id":"9e46746615ea2073f121ddeb100891e9","-shortcut":"/04","-tip":"吹口哨","-multiframe":"1","FILE_ORG":"04.png","FILE_FIXED":"04.png"},{"-id":"3b858ec5cd72e647eab1562474e9d7ec","-shortcut":"/10","-tip":"我来啦","-multiframe":"2","FILE_ORG":"10.png","FILE_FIXED":"10.png"},{"-id":"95a8406419b66fe8343671fc1de96b04","-shortcut":"/02","-tip":"棒棒哒","-multiframe":"4","FILE_ORG":"02.png","FILE_FIXED":"02.png"},{"-id":"80b7d5cb192146cf4e9786ab0154b544","-shortcut":"/03","-tip":"不如跳舞","-multiframe":"5","FILE_ORG":"03.png","FILE_FIXED":"03.png"},{"-id":"7f2cd29e89b9db906c923ea73d572809","-shortcut":"/05","-tip":"多喝冰水","-multiframe":"5","FILE_ORG":"05.png","FILE_FIXED":"05.png"},{"-id":"3765b16fdafa2a1e3281e3c8b3f07e30","-shortcut":"/06","-tip":"慌的一批","-multiframe":"17","FILE_ORG":"06.png","FILE_FIXED":"06.png"},{"-id":"804aacf314fdc19a752bf4646e760b3f","-shortcut":"/13","-tip":"雨女无瓜","-multiframe":"5","FILE_ORG":"13.png","FILE_FIXED":"13.png"}]}}
     */

    private FACESETTINGBean FACESETTING;

    public FACESETTINGBean getFACESETTING() {
        return FACESETTING;
    }

    public void setFACESETTING(FACESETTINGBean FACESETTING) {
        this.FACESETTING = FACESETTING;
    }

    public static class FACESETTINGBean {
        /**
         * DEFAULTFACE : {"-emotionName":"Star_hegemony","-version":"1000","-count":"17","-showall":"0","-line":"5","-Width":"24","-Height":"24","-package":"Star_hegemony","FACE":[{"-id":"cdd7494956dd0defd182a4244b19d83f","-shortcut":"/ok","-tip":"OK","-multiframe":"4","FILE_ORG":"OK.png","FILE_FIXED":"OK.png"},{"-id":"187f03e1caa406a5fc6cf55c8bd135d6","-shortcut":"/sayhi","-tip":"say hi","-multiframe":"3","FILE_ORG":"say-hi.png","FILE_FIXED":"say-hi.png"},{"-id":"0df4c667df602869c0741f1517fb0ddc","-shortcut":"/heng","-tip":"heng","-multiframe":"5","FILE_ORG":"heng.png","FILE_FIXED":"heng.png"},{"-id":"f79f75cf746ee63bbb759f902b7de7cb","-shortcut":"/09","-tip":"委屈","-multiframe":"4","FILE_ORG":"09.png","FILE_FIXED":"09.png"},{"-id":"69e440d0bd443894c194b9fe8c1cd103","-shortcut":"/11","-tip":"心塞","-multiframe":"10","FILE_ORG":"11.png","FILE_FIXED":"11.png"},{"-id":"ecf494d8ac668246672ec06dacafa2d3","-shortcut":"/12","-tip":"星星","-multiframe":"24","FILE_ORG":"12.png","FILE_FIXED":"12.png"},{"-id":"015df3e1c164f6afd302d7d912ae4d10","-shortcut":"/08","-tip":"期待","-multiframe":"7","FILE_ORG":"08.png","FILE_FIXED":"08.png"},{"-id":"47c6d627e4161e3c0b8383ea4dc64ccb","-shortcut":"/01","-tip":"白眼","-multiframe":"2","FILE_ORG":"01.png","FILE_FIXED":"01.png"},{"-id":"275bc2321f1cbcd9d13e4146a835b261","-shortcut":"/14","-tip":"种草","-multiframe":"6","FILE_ORG":"14.png","FILE_FIXED":"14.png"},{"-id":"30ea70dd93f86a8d2d136b5431f0c9f2","-shortcut":"/07","-tip":"叫霸霸","-multiframe":"2","FILE_ORG":"07.png","FILE_FIXED":"07.png"},{"-id":"9e46746615ea2073f121ddeb100891e9","-shortcut":"/04","-tip":"吹口哨","-multiframe":"1","FILE_ORG":"04.png","FILE_FIXED":"04.png"},{"-id":"3b858ec5cd72e647eab1562474e9d7ec","-shortcut":"/10","-tip":"我来啦","-multiframe":"2","FILE_ORG":"10.png","FILE_FIXED":"10.png"},{"-id":"95a8406419b66fe8343671fc1de96b04","-shortcut":"/02","-tip":"棒棒哒","-multiframe":"4","FILE_ORG":"02.png","FILE_FIXED":"02.png"},{"-id":"80b7d5cb192146cf4e9786ab0154b544","-shortcut":"/03","-tip":"不如跳舞","-multiframe":"5","FILE_ORG":"03.png","FILE_FIXED":"03.png"},{"-id":"7f2cd29e89b9db906c923ea73d572809","-shortcut":"/05","-tip":"多喝冰水","-multiframe":"5","FILE_ORG":"05.png","FILE_FIXED":"05.png"},{"-id":"3765b16fdafa2a1e3281e3c8b3f07e30","-shortcut":"/06","-tip":"慌的一批","-multiframe":"17","FILE_ORG":"06.png","FILE_FIXED":"06.png"},{"-id":"804aacf314fdc19a752bf4646e760b3f","-shortcut":"/13","-tip":"雨女无瓜","-multiframe":"5","FILE_ORG":"13.png","FILE_FIXED":"13.png"}]}
         */

        private DEFAULTFACEBean DEFAULTFACE;

        public DEFAULTFACEBean getDEFAULTFACE() {
            return DEFAULTFACE;
        }

        public void setDEFAULTFACE(DEFAULTFACEBean DEFAULTFACE) {
            this.DEFAULTFACE = DEFAULTFACE;
        }

        public static class DEFAULTFACEBean {
            /**
             * -emotionName : Star_hegemony
             * -version : 1000
             * -count : 17
             * -showall : 0
             * -line : 5
             * -Width : 24
             * -Height : 24
             * -package : Star_hegemony
             * FACE : [{"-id":"cdd7494956dd0defd182a4244b19d83f","-shortcut":"/ok","-tip":"OK","-multiframe":"4","FILE_ORG":"OK.png","FILE_FIXED":"OK.png"},{"-id":"187f03e1caa406a5fc6cf55c8bd135d6","-shortcut":"/sayhi","-tip":"say hi","-multiframe":"3","FILE_ORG":"say-hi.png","FILE_FIXED":"say-hi.png"},{"-id":"0df4c667df602869c0741f1517fb0ddc","-shortcut":"/heng","-tip":"heng","-multiframe":"5","FILE_ORG":"heng.png","FILE_FIXED":"heng.png"},{"-id":"f79f75cf746ee63bbb759f902b7de7cb","-shortcut":"/09","-tip":"委屈","-multiframe":"4","FILE_ORG":"09.png","FILE_FIXED":"09.png"},{"-id":"69e440d0bd443894c194b9fe8c1cd103","-shortcut":"/11","-tip":"心塞","-multiframe":"10","FILE_ORG":"11.png","FILE_FIXED":"11.png"},{"-id":"ecf494d8ac668246672ec06dacafa2d3","-shortcut":"/12","-tip":"星星","-multiframe":"24","FILE_ORG":"12.png","FILE_FIXED":"12.png"},{"-id":"015df3e1c164f6afd302d7d912ae4d10","-shortcut":"/08","-tip":"期待","-multiframe":"7","FILE_ORG":"08.png","FILE_FIXED":"08.png"},{"-id":"47c6d627e4161e3c0b8383ea4dc64ccb","-shortcut":"/01","-tip":"白眼","-multiframe":"2","FILE_ORG":"01.png","FILE_FIXED":"01.png"},{"-id":"275bc2321f1cbcd9d13e4146a835b261","-shortcut":"/14","-tip":"种草","-multiframe":"6","FILE_ORG":"14.png","FILE_FIXED":"14.png"},{"-id":"30ea70dd93f86a8d2d136b5431f0c9f2","-shortcut":"/07","-tip":"叫霸霸","-multiframe":"2","FILE_ORG":"07.png","FILE_FIXED":"07.png"},{"-id":"9e46746615ea2073f121ddeb100891e9","-shortcut":"/04","-tip":"吹口哨","-multiframe":"1","FILE_ORG":"04.png","FILE_FIXED":"04.png"},{"-id":"3b858ec5cd72e647eab1562474e9d7ec","-shortcut":"/10","-tip":"我来啦","-multiframe":"2","FILE_ORG":"10.png","FILE_FIXED":"10.png"},{"-id":"95a8406419b66fe8343671fc1de96b04","-shortcut":"/02","-tip":"棒棒哒","-multiframe":"4","FILE_ORG":"02.png","FILE_FIXED":"02.png"},{"-id":"80b7d5cb192146cf4e9786ab0154b544","-shortcut":"/03","-tip":"不如跳舞","-multiframe":"5","FILE_ORG":"03.png","FILE_FIXED":"03.png"},{"-id":"7f2cd29e89b9db906c923ea73d572809","-shortcut":"/05","-tip":"多喝冰水","-multiframe":"5","FILE_ORG":"05.png","FILE_FIXED":"05.png"},{"-id":"3765b16fdafa2a1e3281e3c8b3f07e30","-shortcut":"/06","-tip":"慌的一批","-multiframe":"17","FILE_ORG":"06.png","FILE_FIXED":"06.png"},{"-id":"804aacf314fdc19a752bf4646e760b3f","-shortcut":"/13","-tip":"雨女无瓜","-multiframe":"5","FILE_ORG":"13.png","FILE_FIXED":"13.png"}]
             */

            @com.google.gson.annotations.SerializedName("-emotionName")
            private String emotionName;
            @com.google.gson.annotations.SerializedName("-version")
            private String version;
            @com.google.gson.annotations.SerializedName("-count")
            private String count;
            @com.google.gson.annotations.SerializedName("-showall")
            private String showall;
            @com.google.gson.annotations.SerializedName("-line")
            private String line;
            @com.google.gson.annotations.SerializedName("-Width")
            private String Width;
            @com.google.gson.annotations.SerializedName("-Height")
            private String Height;
            @com.google.gson.annotations.SerializedName("-package")
            private String packageX;
            private List<FACEBean> FACE;

            public String getEmotionName() {
                return emotionName;
            }

            public void setEmotionName(String emotionName) {
                this.emotionName = emotionName;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getShowall() {
                return showall;
            }

            public void setShowall(String showall) {
                this.showall = showall;
            }

            public String getLine() {
                return line;
            }

            public void setLine(String line) {
                this.line = line;
            }

            public String getWidth() {
                return Width;
            }

            public void setWidth(String Width) {
                this.Width = Width;
            }

            public String getHeight() {
                return Height;
            }

            public void setHeight(String Height) {
                this.Height = Height;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public List<FACEBean> getFACE() {
                return FACE;
            }

            public void setFACE(List<FACEBean> FACE) {
                this.FACE = FACE;
            }

            public static class FACEBean {
                /**
                 * -id : cdd7494956dd0defd182a4244b19d83f
                 * -shortcut : /ok
                 * -tip : OK
                 * -multiframe : 4
                 * FILE_ORG : OK.png
                 * FILE_FIXED : OK.png
                 */

                @com.google.gson.annotations.SerializedName("-id")
                private String id;
                @com.google.gson.annotations.SerializedName("-shortcut")
                private String shortcut;
                @com.google.gson.annotations.SerializedName("-tip")
                private String tip;
                @com.google.gson.annotations.SerializedName("-multiframe")
                private String multiframe;
                private String FILE_ORG;
                private String FILE_FIXED;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getShortcut() {
                    return shortcut;
                }

                public void setShortcut(String shortcut) {
                    this.shortcut = shortcut;
                }

                public String getTip() {
                    return tip;
                }

                public void setTip(String tip) {
                    this.tip = tip;
                }

                public String getMultiframe() {
                    return multiframe;
                }

                public void setMultiframe(String multiframe) {
                    this.multiframe = multiframe;
                }

                public String getFILE_ORG() {
                    return FILE_ORG;
                }

                public void setFILE_ORG(String FILE_ORG) {
                    this.FILE_ORG = FILE_ORG;
                }

                public String getFILE_FIXED() {
                    return FILE_FIXED;
                }

                public void setFILE_FIXED(String FILE_FIXED) {
                    this.FILE_FIXED = FILE_FIXED;
                }
            }
        }
    }
}
