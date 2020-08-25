package bot.enums

/**
 * An enum representing each type of environment the project can be in
 *
 * @param value The corresponding value of the environment
 */
enum class Environments(private val value: String) {
    DEVELOPMENT("DEVELOPMENT"),
    TEST("TEST"),
    PRODUCTION("PRODUCTION")
}
