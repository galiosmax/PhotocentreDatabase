package photocentre.dao

import photocentre.dataClasses.Film
import java.sql.Statement
import javax.sql.DataSource

class FilmDao(private val dataSource: DataSource) {

    fun createFilm(toCreate: Film): Long {
        val statement = dataSource.connection.prepareStatement(
                "insert into films (film_name, sold_item_id, order_id) values(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, toCreate.name)
        statement.setLong(2, toCreate.soldItem?.id!!)
        statement.setLong(3, toCreate.order.id!!)
        statement.executeUpdate()
        val generated = statement.generatedKeys
        generated.next()
        return generated.getLong(1)
    }

    fun createFilms(toCreate: Iterable<Film>): List<Long> {
        val statement = dataSource.connection.prepareStatement(
                "insert into films (film_name, sold_item_id, order_id) values(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)

        for (film in toCreate) {
            statement.setString(1, film.name)
            statement.setLong(2, film.soldItem?.id!!)
            statement.setLong(3, film.order.id!!)
            statement.addBatch()
        }

        statement.executeUpdate()
        val generated = statement.generatedKeys
        val res = ArrayList<Long>()
        while(generated.next()) {
            res += generated.getLong(1)
        }
        return res
    }

    //TODO REWRITE LAZY LOADING
    /* fun findFilm(id: Long): Film? {
         val statement = dataSource.connection.prepareStatement("SELECT film_id, film_name, professional_id, amateur_id FROM films WHERE film_id = ?")
         statement.setLong(1, id)
         val resultSet = statement.executeQuery()
         return if (resultSet.next()) {
             //if (resultSet.getLong("professional_id") != null) {
             //}
             Film(resultSet.getLong("film_id"),
                     resultSet.getString("film_name"),
                     Professional(resultSet.getLong("professional_id"), ),
                     null)
         } else {
             null
         }
     }*/

    fun updateFilm(film: Film) {
        val statement = dataSource.connection.prepareStatement(
                "update films set film_name = ?, sold_item_id = ?, order_id = ? where film_id = ?")
        statement.setString(1, film.name)
        statement.setLong(2, film.soldItem?.id!!)
        statement.setLong(3, film.order.id!!)
        statement.setLong(4, film.id!!)

        statement.executeUpdate()
    }

    fun deleteFilm(id: Long) {
        val statement = dataSource.connection.prepareStatement(
                "delete from films where film_id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }
}