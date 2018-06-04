import com.coxautodev.graphql.tools.SchemaParser
import graphql.servlet.SimpleGraphQLServlet

import javax.servlet.annotation.WebServlet
import graphql.schema.GraphQLSchema
import java.sql.Connection
import java.sql.SQLException


@WebServlet(urlPatterns = arrayOf("/graphql"))
class GraphQLEndpoint : SimpleGraphQLServlet(buildSchema())

private fun buildSchema(): GraphQLSchema {
    val linkRepository = LinkRepository()
    /*val conn :Connection = createConnection()
    val vesselRepository = VesselRepository(conn)*/
    return SchemaParser.newParser()
            .file("schema.graphqls")
            .resolvers(Query(linkRepository),Mutation(linkRepository))
            .build()
            .makeExecutableSchema()
}