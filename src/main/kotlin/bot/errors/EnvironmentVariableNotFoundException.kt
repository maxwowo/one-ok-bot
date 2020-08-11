package bot.errors

class EnvironmentVariableNotFoundException(variable: String) : Exception("Environment variable $variable not found")
