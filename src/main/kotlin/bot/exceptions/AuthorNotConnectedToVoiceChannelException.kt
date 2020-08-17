package bot.exceptions

import net.dv8tion.jda.api.entities.User

class AuthorNotConnectedToVoiceChannelException(author: User) : Exception(author.id)
