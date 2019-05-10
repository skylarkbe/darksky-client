package be.skylark.weather.darkskyclient.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * This enum represents a Dark Sky Language that can be used in the API call.
 * See <a href="https://darksky.net/dev/docs">DarkSky API</a> for more information
 * @author Skylark.be
 */
public enum DsLanguage {

    AR("ar"), // Arabic
    AZ("az"), // Azerbaijani
    BE("be"), // Belarusian
    BG("bg"), //  Bulgarian
    BN("bn"), //  Bengali
    BS("bs"), //  Bosnian
    CA("ca"), //  Catalan
    CS("cs"), //  Czech
    DA("da"), //  Danish
    DE("de"), //  German
    EL("el"), //  Greek
    EN("en"), //  English (which is the default")
    EO("eo"), //  Esperanto
    ES("es"), //  Spanish
    ET("et"), //  Estonian
    FI("fi"), //  Finnish
    FR("fr"), //  French
    HE("he"), //  Hebrew
    HI("hi"), //  Hindi
    HR("hr"), //  Croatian
    HU("hu"), //  Hungarian
    ID("id"), //  Indonesian
    IS("is"), //  Icelandic
    IT("it"), //  Italian
    JA("ja"), //  Japanese
    KA("ka"), //  Georgian
    KN("kn"), //  Kannada
    KO("ko"), //  Korean
    KW("kw"), //  Cornish
    LV("lv"), //  Latvian
    ML("ml"), //  Malayam
    MR("mr"), //  Marathi
    NB("nb"), //  Norwegian Bokmål
    NL("nl"), //  Dutch
    NO("no"), //  Norwegian Bokmål (alias for nb")
    PA("pa"), //  Punjabi
    PL("pl"), //  Polish
    PT("pt"), //  Portuguese
    RO("ro"), //  Romanian
    RU("ru"), //  Russian
    SK("sk"), //  Slovak
    SL("sl"), //  Slovenian
    SR("sr"), //  Serbian
    SV("sv"), //  Swedish
    TA("ta"), //  Tamil
    TE("te"), //  Telugu
    TET("tet"), //  Tetum
    TR("tr"), //  Turkish
    UK("uk"), //  Ukrainian
    UR("ur"), //  Urdu " +
    X_PIG("x-pig-latin"), //  Igpay Atinlay
    ZH("zh"), //  simplified Chinese
    ZH_TW("zh-tw"); //  traditional Chinese

    @Getter private String value ;

    DsLanguage(String value) {
        this.value = value ;
    }

    public static DsLanguage findLanguageByValue(final String value) {
        Optional<DsLanguage> optionalLanguage = Arrays.stream( DsLanguage.values() )
                .filter( language -> language.getValue().equals(value) )
                .findFirst() ;
        if ( optionalLanguage.isPresent() ) {
            return optionalLanguage.get() ;
        }
        return EN ;
    }
}
