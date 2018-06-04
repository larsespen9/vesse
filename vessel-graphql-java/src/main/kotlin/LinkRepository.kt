
import java.util.ArrayList


class LinkRepository {

    private val links: MutableList<Link>

    val allLinks: List<Link>
        get() = links

    init {
        links = ArrayList()
        //add some links to start off with
        links.add(Link(1,"http://howtographql.com", "Your favorite GraphQL page"))
        links.add(Link(2,"http://graphql.org/learn/", "The official docks"))
    }

    fun saveLink(link: Link) {
        links.add(link)
    }
}