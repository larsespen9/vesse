import com.coxautodev.graphql.tools.SchemaParser
import graphql.servlet.SimpleGraphQLServlet

import javax.servlet.annotation.WebServlet
import graphql.schema.GraphQLSchema



@WebServlet(urlPatterns = arrayOf("/graphql"))
class GraphQLEndpoint : SimpleGraphQLServlet(buildSchema())

private fun buildSchema(): GraphQLSchema? {
        val linkRepository = LinkRepository()
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(Query(linkRepository))
                .build()
                .makeExecutableSchema()
    }

