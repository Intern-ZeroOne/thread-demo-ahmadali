package uz.akh.auth


enum class GrantType(val value: String) {
    REFRESH_TOKEN("refresh_token"),
    PASSWORD("password"),
    SIGNATURE("sign"),
    MFID("mf"),
    ONEID("oneid"),
    EIMZO_MOBILE("eimzo_mobile")
}