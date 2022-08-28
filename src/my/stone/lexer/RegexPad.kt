@file:JvmName("RegexPad")

package my.stone.lexer

private const val SPACE_SEQUENCE = "\\s*"
private const val COMMENT = "//.*"
private const val NUMBER_LITERAL = "[0-9]+"
private const val STRING_LITERAL = "\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\""
private const val IDENTIFIER = "[A-Z_a-z][A-Z_a-z0-9]*"
// memo: \p{Punct}	句読文字:!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ のいずれか
private const val SIGN_IDENTIFIER = "==|<=|>=|&&|\\|\\||\\p{Punct}"

fun getRegexPad() =
    "${SPACE_SEQUENCE}((${COMMENT})|(${NUMBER_LITERAL})|(${STRING_LITERAL})|${IDENTIFIER}|${SIGN_IDENTIFIER})?"

enum class RegexPadGroup(val group: Int) {
    NON_SPACE(1),
    COMMENT(2),
    NUMBER_LITERAL(3),
    STRING_LITERAL(4),
}



