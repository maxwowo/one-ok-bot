package bot.exceptions

class EnvironmentVariableNotFoundException(variable: String) : Exception("Environment variable $variable not found")
