import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

class VesselRepository(val conn: Connection){
    private val vesselList: MutableList<Vessel>

    val allVessels : List<Vessel>
        get() = vesselList

    init {
        vesselList = ArrayList()
    }
    @Throws(SQLException::class)
    fun getVesselList():ArrayList<Vessel>{
        val resultSet:ResultSet = executeQuery(conn,"select * from vessel")
        while (resultSet.next()) {
            val imo = resultSet.getInt("imo")
            val vesselname = resultSet.getString("vesselname")
            val mmsi = resultSet.getInt("mmsi")
            val vesseltype = resultSet.getString("vesseltype")
            val grosstannage = resultSet.getFloat("grosstonnage")
            val dwt = resultSet.getFloat("dwt")
            val flag = resultSet.getString("flag")
            val build = resultSet.getInt("build")
            vesselList.add(Vessel(imo,vesselname,mmsi,vesseltype,grosstannage,dwt,flag,build))
        }
        resultSet.close()
        return vesselList as ArrayList<Vessel>
    }

}