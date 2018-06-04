
import com.coxautodev.graphql.tools.GraphQLRootResolver


class Mutation(private val linkRepository: LinkRepository) : GraphQLRootResolver {

    fun createLink(id: Int,url: String, description: String): Link {
        val newLink = Link(id,url, description)
        linkRepository.saveLink(newLink)
        return newLink
    }
}