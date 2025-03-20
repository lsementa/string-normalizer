import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class StringNormalizer {
    private static final Map<Character, String> specialReplacements = new HashMap<>();
    static {
        // Scandinavian
        specialReplacements.put('ø', "o");
        specialReplacements.put('Ø', "O");
        specialReplacements.put('æ', "ae");
        specialReplacements.put('Æ', "AE");
        specialReplacements.put('å', "a");
        specialReplacements.put('Å', "A");

        // German
        specialReplacements.put('ß', "ss");
        specialReplacements.put('ẞ', "SS"); // Capital ß

        // French Ligatures
        specialReplacements.put('œ', "oe");
        specialReplacements.put('Œ', "OE");

        // Polish
        specialReplacements.put('ł', "l");
        specialReplacements.put('Ł', "L");

        // Croatian/Slovak/Slovenian
        specialReplacements.put('đ', "d");
        specialReplacements.put('Đ', "D");

        // Icelandic/Old English
        specialReplacements.put('þ', "th");
        specialReplacements.put('Þ', "Th");
        specialReplacements.put('ð', "d");
        specialReplacements.put('Ð', "D");

        // Turkish
        specialReplacements.put('ğ', "g");
        specialReplacements.put('Ğ', "G");
        specialReplacements.put('ı', "i"); // dotless i
        specialReplacements.put('İ', "I"); // dotted I
        specialReplacements.put('ş', "s");
        specialReplacements.put('Ş', "S");

        // Czech/Slovak
        specialReplacements.put('č', "c");
        specialReplacements.put('Č', "C");
        specialReplacements.put('ř', "r");
        specialReplacements.put('Ř', "R");
        specialReplacements.put('š', "s");
        specialReplacements.put('Š', "S");
        specialReplacements.put('ž', "z");
        specialReplacements.put('Ž', "Z");

        // Hungarian
        specialReplacements.put('ő', "o");
        specialReplacements.put('Ő', "O");
        specialReplacements.put('ű', "u");
        specialReplacements.put('Ű', "U");

        // Romanian
        specialReplacements.put('ă', "a");
        specialReplacements.put('Ă', "A");
        specialReplacements.put('ș', "s");
        specialReplacements.put('Ș', "S");
        specialReplacements.put('ț', "t");
        specialReplacements.put('Ț', "T");

        // Cyrillic (basic transliteration)
        specialReplacements.put('а', "a");
        specialReplacements.put('б', "b");
        specialReplacements.put('в', "v");
        specialReplacements.put('г', "g");
        specialReplacements.put('д', "d");
        specialReplacements.put('е', "e");
        specialReplacements.put('ё', "yo");
        specialReplacements.put('ж', "zh");
        specialReplacements.put('з', "z");
        specialReplacements.put('и', "i");
        specialReplacements.put('й', "y");
        specialReplacements.put('к', "k");
        specialReplacements.put('л', "l");
        specialReplacements.put('м', "m");
        specialReplacements.put('н', "n");
        specialReplacements.put('о', "o");
        specialReplacements.put('п', "p");
        specialReplacements.put('р', "r");
        specialReplacements.put('с', "s");
        specialReplacements.put('т', "t");
        specialReplacements.put('у', "u");
        specialReplacements.put('ф', "f");
        specialReplacements.put('х', "kh");
        specialReplacements.put('ц', "ts");
        specialReplacements.put('ч', "ch");
        specialReplacements.put('ш', "sh");
        specialReplacements.put('щ', "shch");
        specialReplacements.put('ъ', "");
        specialReplacements.put('ы', "y");
        specialReplacements.put('ь', "");
        specialReplacements.put('э', "e");
        specialReplacements.put('ю', "yu");
        specialReplacements.put('я', "ya");

        // Cyrillic uppercase
        specialReplacements.put('А', "A");
        specialReplacements.put('Б', "B");
        specialReplacements.put('В', "V");
        specialReplacements.put('Г', "G");
        specialReplacements.put('Д', "D");
        specialReplacements.put('Е', "E");
        specialReplacements.put('Ё', "Yo");
        specialReplacements.put('Ж', "Zh");
        specialReplacements.put('З', "Z");
        specialReplacements.put('И', "I");
        specialReplacements.put('Й', "Y");
        specialReplacements.put('К', "K");
        specialReplacements.put('Л', "L");
        specialReplacements.put('М', "M");
        specialReplacements.put('Н', "N");
        specialReplacements.put('О', "O");
        specialReplacements.put('П', "P");
        specialReplacements.put('Р', "R");
        specialReplacements.put('С', "S");
        specialReplacements.put('Т', "T");
        specialReplacements.put('У', "U");
        specialReplacements.put('Ф', "F");
        specialReplacements.put('Х', "Kh");
        specialReplacements.put('Ц', "Ts");
        specialReplacements.put('Ч', "Ch");
        specialReplacements.put('Ш', "Sh");
        specialReplacements.put('Щ', "Shch");
        specialReplacements.put('Ъ', "");
        specialReplacements.put('Ы', "Y");
        specialReplacements.put('Ь', "");
        specialReplacements.put('Э', "E");
        specialReplacements.put('Ю', "Yu");
        specialReplacements.put('Я', "Ya");

        // Greek (most common transliterations)
        specialReplacements.put('α', "a");
        specialReplacements.put('β', "b");
        specialReplacements.put('γ', "g");
        specialReplacements.put('δ', "d");
        specialReplacements.put('ε', "e");
        specialReplacements.put('ζ', "z");
        specialReplacements.put('η', "i");  // Changed from 'h' to 'i' for modern Greek
        specialReplacements.put('θ', "th");
        specialReplacements.put('ι', "i");
        specialReplacements.put('κ', "k");
        specialReplacements.put('λ', "l");
        specialReplacements.put('μ', "m");
        specialReplacements.put('ν', "n");
        specialReplacements.put('ξ', "x");
        specialReplacements.put('ο', "o");
        specialReplacements.put('π', "p");
        specialReplacements.put('ρ', "r");
        specialReplacements.put('σ', "s");
        specialReplacements.put('ς', "s");  // Final sigma
        specialReplacements.put('τ', "t");
        specialReplacements.put('υ', "y");  // Changed from 'u' to 'y' for modern Greek
        specialReplacements.put('φ', "f");
        specialReplacements.put('χ', "ch");
        specialReplacements.put('ψ', "ps");
        specialReplacements.put('ω', "o");
        specialReplacements.put('ϑ', "th"); // Theta symbol
        specialReplacements.put('ϒ', "Y");  // Upsilon with hook
        specialReplacements.put('ϖ', "p");  // Variant pi

        // Greek uppercase
        specialReplacements.put('Α', "A");
        specialReplacements.put('Β', "B");
        specialReplacements.put('Γ', "G");
        specialReplacements.put('Δ', "D");
        specialReplacements.put('Ε', "E");
        specialReplacements.put('Ζ', "Z");
        specialReplacements.put('Η', "I");  // Changed from 'H' to 'I' for modern Greek
        specialReplacements.put('Θ', "Th");
        specialReplacements.put('Ι', "I");
        specialReplacements.put('Κ', "K");
        specialReplacements.put('Λ', "L");
        specialReplacements.put('Μ', "M");
        specialReplacements.put('Ν', "N");
        specialReplacements.put('Ξ', "X");
        specialReplacements.put('Ο', "O");
        specialReplacements.put('Π', "P");
        specialReplacements.put('Ρ', "R");
        specialReplacements.put('Σ', "S");
        specialReplacements.put('Τ', "T");
        specialReplacements.put('Υ', "Y");  // Changed from 'U' to 'Y' for modern Greek
        specialReplacements.put('Φ', "F");
        specialReplacements.put('Χ', "Ch");
        specialReplacements.put('Ψ', "Ps");
        specialReplacements.put('Ω', "O");

        // Arabic-Indic digits
        specialReplacements.put('٠', "0");
        specialReplacements.put('١', "1");
        specialReplacements.put('٢', "2");
        specialReplacements.put('٣', "3");
        specialReplacements.put('٤', "4");
        specialReplacements.put('٥', "5");
        specialReplacements.put('٦', "6");
        specialReplacements.put('٧', "7");
        specialReplacements.put('٨', "8");
        specialReplacements.put('٩', "9");

        // Eastern Arabic-Indic digits
        specialReplacements.put('۰', "0");
        specialReplacements.put('۱', "1");
        specialReplacements.put('۲', "2");
        specialReplacements.put('۳', "3");
        specialReplacements.put('۴', "4");
        specialReplacements.put('۵', "5");
        specialReplacements.put('۶', "6");
        specialReplacements.put('۷', "7");
        specialReplacements.put('۸', "8");
        specialReplacements.put('۹', "9");

        // Currency symbols
        specialReplacements.put('€', "EUR");
        specialReplacements.put('£', "GBP");
        specialReplacements.put('¥', "JPY");
        specialReplacements.put('₹', "INR");
        specialReplacements.put('₽', "RUB");
        specialReplacements.put('¢', "cent");

        // Mathematical symbols
        specialReplacements.put('×', "x");
        specialReplacements.put('÷', "/");
        specialReplacements.put('±', "+/-");
        specialReplacements.put('≠', "!=");
        specialReplacements.put('≤', "<=");
        specialReplacements.put('≥', ">=");
        specialReplacements.put('∞', "[infinity]");
        specialReplacements.put('√', "[sqrt]");
        specialReplacements.put('∑', "[sum]");
        specialReplacements.put('∫', "[integral]");
        specialReplacements.put('∆', "[delta]");
        specialReplacements.put('∇', "[nabla]");
        specialReplacements.put('∼', "~");
        specialReplacements.put('≈', "~~");
        specialReplacements.put('≡', "===");
        specialReplacements.put('∝', "[proportional to]");
        specialReplacements.put('∴', "[therefore]");
        specialReplacements.put('∵', "[because]");
        specialReplacements.put('∂', "[partial]");
        specialReplacements.put('∀', "[for all]");
        specialReplacements.put('∃', "[there exists]");
        specialReplacements.put('∅', "[empty set]");
        specialReplacements.put('∈', "[in]");
        specialReplacements.put('∉', "[not in]");

        // Fullwidth punctuation replacements
        specialReplacements.put('！', "!");  // Fullwidth exclamation mark
        specialReplacements.put('？', "?");  // Fullwidth question mark
        specialReplacements.put('（', "(");  // Fullwidth left parenthesis
        specialReplacements.put('）', ")");  // Fullwidth right parenthesis
        specialReplacements.put('［', "[");  // Fullwidth left square bracket
        specialReplacements.put('］', "]");  // Fullwidth right square bracket
        specialReplacements.put('｛', "{");  // Fullwidth left curly bracket
        specialReplacements.put('｝', "}");  // Fullwidth right curly bracket
        specialReplacements.put('〈', "<");  // Fullwidth less-than sign
        specialReplacements.put('〉', ">");  // Fullwidth greater-than sign
        specialReplacements.put('．', ".");  // Fullwidth period
        specialReplacements.put('，', ",");  // Fullwidth comma
        specialReplacements.put('；', ";");  // Fullwidth semicolon
        specialReplacements.put('：', ":");  // Fullwidth colon
        specialReplacements.put('／', "/");  // Fullwidth solidus (slash)
        specialReplacements.put('＼', "\\"); // Fullwidth reverse solidus (backslash)
        specialReplacements.put('＋', "+");  // Fullwidth plus sign
        specialReplacements.put('－', "-");  // Fullwidth minus sign
        specialReplacements.put('＝', "=");  // Fullwidth equals sign
        specialReplacements.put('＊', "*");  // Fullwidth asterisk
        specialReplacements.put('＆', "&");  // Fullwidth ampersand
        specialReplacements.put('＃', "#");  // Fullwidth number sign
        specialReplacements.put('％', "%");  // Fullwidth percent sign
        specialReplacements.put('＠', "@");  // Fullwidth at sign
        specialReplacements.put('｜', "|");  // Fullwidth vertical bar

        // Punctuation and other symbols
        specialReplacements.put('©', "(c)");
        specialReplacements.put('®', "(r)");
        specialReplacements.put('™', "(tm)");
        specialReplacements.put('¡', "!");
        specialReplacements.put('¿', "?");
        specialReplacements.put('«', "\"");
        specialReplacements.put('»', "\"");
        specialReplacements.put('¶', "P");     // Pilcrow sign (paragraph symbol)
        specialReplacements.put('§', "Section");
        specialReplacements.put('‾', "-");     // Overline
        specialReplacements.put('¤', "[]");

        // Arrows and directional symbols
        specialReplacements.put('←', "<-");
        specialReplacements.put('→', "->");
        specialReplacements.put('↑', "^");
        specialReplacements.put('↓', "v");
        specialReplacements.put('↔', "<->");
        specialReplacements.put('⇐', "<=");
        specialReplacements.put('⇒', "=>");
        specialReplacements.put('⇑', "^^");
        specialReplacements.put('⇓', "vv");
        specialReplacements.put('↩', "<-");  // Leftward arrow with hook
        specialReplacements.put('↪', "->");  // Rightward arrow with hook

        // Various quotation marks
        specialReplacements.put((char) 0x201C, "\"");  // Left double quotation mark
        specialReplacements.put((char) 0x201D, "\"");  // Right double quotation mark
        specialReplacements.put((char) 0x2018, "'");   // Left single quotation mark
        specialReplacements.put((char) 0x2019, "'");   // Right single quotation mark
        specialReplacements.put((char) 0x201E, "\"");  // Double low-9 quotation mark
        specialReplacements.put((char) 0x201A, "'");   // Single low-9 quotation mark
        specialReplacements.put((char) 0x301D, "\"");  // Reversed double prime quotation mark
        specialReplacements.put((char) 0x301E, "\"");  // Double prime quotation mark
        specialReplacements.put((char) 0x2039, "<");   // Single left-pointing angle quotation
        specialReplacements.put((char) 0x203A, ">");   // Single right-pointing angle quotation

        // Dashes and hyphens
        specialReplacements.put((char) 0x2013, "-");   // En dash
        specialReplacements.put((char) 0x2014, "--");  // Em dash
        specialReplacements.put((char) 0x2015, "--");  // Horizontal bar
        specialReplacements.put((char) 0x2010, "-");   // Hyphen
        specialReplacements.put((char) 0x2011, "-");   // Non-breaking hyphen
        specialReplacements.put((char) 0x2012, "-");   // Figure dash
        specialReplacements.put((char) 0x2043, "-");   // Hyphen bullet
        specialReplacements.put((char) 0x207B, "-");   // Superscript minus
        specialReplacements.put((char) 0x208B, "-");   // Subscript minus
        specialReplacements.put('\u00AD', "");  // Soft hyphen

        // Dots and ellipses
        specialReplacements.put((char) 0x00B7, "."); // Middle dot
        specialReplacements.put((char) 0x2022, "*"); // Bullet
        specialReplacements.put((char) 0x2023, ">"); // Triangular bullet
        specialReplacements.put((char) 0x2026, "..."); // Ellipsis
        specialReplacements.put((char) 0x22EF, "..."); // Midline horizontal ellipsis
        specialReplacements.put((char) 0x22EE, "..."); // Vertical ellipsis
        specialReplacements.put((char) 0x22F0, "..."); // Up right diagonal ellipsis
        specialReplacements.put((char) 0x22F1, "..."); // Down right diagonal ellipsis

        // Spaces
        specialReplacements.put('\u00A0', " "); // Non-breaking space
        specialReplacements.put('\u2007', " "); // Figure space
        specialReplacements.put('\u202F', " "); // Narrow no-break space
        specialReplacements.put('\u2060', "");  // Word joiner
        specialReplacements.put('\u200B', "");  // Zero-width space
        specialReplacements.put('\u200C', "");  // Zero-width non-joiner
        specialReplacements.put('\u200D', "");  // Zero-width joiner
        specialReplacements.put('\uFEFF', "");  // Zero-width no-break space (BOM)

        // Fraction symbols
        specialReplacements.put('½', "1/2");
        specialReplacements.put('⅓', "1/3");
        specialReplacements.put('⅔', "2/3");
        specialReplacements.put('¼', "1/4");
        specialReplacements.put('¾', "3/4");
        specialReplacements.put('⅕', "1/5");
        specialReplacements.put('⅖', "2/5");
        specialReplacements.put('⅗', "3/5");
        specialReplacements.put('⅘', "4/5");
        specialReplacements.put('⅙', "1/6");
        specialReplacements.put('⅚', "5/6");
        specialReplacements.put('⅛', "1/8");
        specialReplacements.put('⅜', "3/8");
        specialReplacements.put('⅝', "5/8");
        specialReplacements.put('⅞', "7/8");

        // Other common symbols
        specialReplacements.put('°', "degrees");
        specialReplacements.put('µ', "micro");
        specialReplacements.put('¬', "not");
        specialReplacements.put('¦', "|");
        specialReplacements.put('ª', "a");  // Feminine ordinal indicator
        specialReplacements.put('º', "o");  // Masculine ordinal indicator
    }

    // Method to replace diacritics and normalize text
    public static String replaceDiacritics(String input) {
        if (input == null) {
            return null;
        }
        // Normalize the string to decomposed form (NFD)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Remove diacritic marks
        String withoutDiacritics = normalized.replaceAll("\\p{M}", "");
        // Manually replace special characters
        StringBuilder result = new StringBuilder();
        for (char c : withoutDiacritics.toCharArray()) {
            result.append(specialReplacements.getOrDefault(c, String.valueOf(c)));
        }
        return result.toString();
    }

    // Method to replace non-ASCII characters with HTML entity
    public static String replaceNonAscii(String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c <= 127) {
                sb.append(c); // Keep
            } else {
                sb.append("&#").append((int) c).append(";"); // Convert
            }
        }

        return sb.toString();
    }

    // Method to lookup non-ASCII characters based on mode
    public static String nonAsciiLookup(String input, String mode) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c <= 127) {
                sb.append(c); // Keep ASCII characters
            } else {
                switch (mode.toLowerCase()) {
                    case "html":
                        sb.append("&#").append((int) c).append(";"); // HTML entity
                        break;
                    case "unicode":
                        sb.append(String.format("\\u%04X", (int) c)); // Unicode escape
                        break;
                    case "hex":
                        sb.append(String.format("0x%04X", (int) c)); // Hexadecimal
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid mode. Use 'html', 'unicode', or 'hex'.");
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "Sørensen",
                "Ærø",
                "Blåbær",
                "Straße",
                "François",
                "Yücetürk",
                "成田市",
                "Güzelbahçe",
                "堺市",
                "São Paulo",
                "Düsseldorf",
                "Montréal",
                "Iwakuni–shi",
                "Þorlákshöfn"
        };

        // Normalize/Replace testing
        System.out.println("\nExamples of normalizing and replacing non-ascii:");
        for (String input : testCases) {
            String output = replaceNonAscii(replaceDiacritics(input));
            // Trim and remove consecutive spaces
            output = output.replaceAll("\\s{2,}", " ").trim();
            System.out.println(input + " → " + output);
        }

        // Non-ASCII lookup
        String input = "ß";
        System.out.println("\nNon-ASCII lookup: " + input);
        System.out.println("HTML: " + nonAsciiLookup(input, "html"));
        System.out.println("Unicode: " + nonAsciiLookup(input, "unicode")); // Unicode escape
        System.out.println("Hex: " + nonAsciiLookup(input, "hex"));
    }
}