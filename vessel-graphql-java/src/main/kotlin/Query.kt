
import com.coxautodev.graphql.tools.GraphQLRootResolver


class Query(private val linkRepository: LinkRepository/*,
            private val vesselRepository: VesselRepository*/) : GraphQLRootResolver {

    fun allLinks(): List<Link> {
        return linkRepository.allLinks
    }
    /*
    fun allVessels(): List<Vessel>{
        return vesselRepository.allVessels
    }
    */
}