import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException


fun main(args: Array<String>){
    val conn = createConnection()
    if (conn != null){
        executeQuery(conn, "select * from vessel")
        conn.close()
    }
}
fun createConnection(): Connection{
    val user = "root"
    val password = "0tEIyFimq3LoAcEJ"
    val cloudSQLIpAddress = "130.211.95.197"
    val port = "3306"
    val database = "vessel_master_data"

    val databaseurl = "jdbc:mysql://"+cloudSQLIpAddress+":"+port+"/"+database + "?verifyServerCertificate=false&useSSL=true"

    try{
        Class.forName("com.mysql.jdbc.Driver")
        var conn = DriverManager.getConnection(databaseurl,user,password)
        return conn
    }catch (e: SQLException){
        e.printStackTrace()
    }catch (e:Exception){
        e.printStackTrace()
    }
    throw error("Det var ikke mulig å opprette connection med databasen")

}
fun executeQuery(conn:Connection, querystring: String):ResultSet{
    try {
        var stmt = conn.createStatement()
        try {
            var result = stmt.executeQuery(querystring)
            return result
        }catch (e:SQLException){
            e.printStackTrace()
        }
    }catch (e:SQLException){
        e.printStackTrace()
    }


    /*
    try {
        writeMetaData(query)
        writeResultSet(query)
    }catch (e:SQLException){
        print("This is probably related to a wrong column name in the resultSet.Get.... functions ")
        e.printStackTrace()
    }finally {
        query.close()
    }*/
    throw error("The connection or query value failed in getting : $querystring")
}

@Throws(SQLException::class)
private fun writeMetaData(resultSet: ResultSet) {
    //  Now get some metadata from the database
    // Result set get the result of the SQL query

    println("The columns in the table are: ")

    println("Table: " + resultSet.metaData.getTableName(1))
    for (i in 1..resultSet.metaData.columnCount) {
        println("Column " + i + " " + resultSet.metaData.getColumnName(i))
    }
}

@Throws(SQLException::class)
private fun writeResultSet(resultSet: ResultSet) {
    print(resultSet.getObject(0))
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
        // It is possible to get the columns via name
        // also possible to get the columns via the column number
        // which starts at 1
        // e.g. resultSet.getSTring(2);
        val imo = resultSet.getInt("imo")
        val vesselname = resultSet.getString("vesselname")
        val mmsi = resultSet.getInt("mmsi")
        val vesseltype = resultSet.getString("vesseltype")
        val grosstannage = resultSet.getInt("grosstonnage")
        val dwt = resultSet.getInt("dwt")
        val flag = resultSet.getString("flag")
        val build = resultSet.getInt("build")
        println("imo: $imo, vesselname: $vesselname, mmsi: $mmsi, vesseltype: $vesseltype, grosstonnage: $grosstannage, dwt: $dwt, build: $build, flag: $flag")
    }
    resultSet.close()
}


