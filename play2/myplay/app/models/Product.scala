package models
import play.api.db.DB
import play.api.Play.current
import anorm._
import anorm.SqlParser.{str, int}

/**
  * Created by zhaoshoulai on 2017/5/23.
  */
case class Product(id:Long , name:String)
object Product{
  val defaultParser = int("id") ~ str("name") map {
    case id ~ name => Product(id, name)
  }

  def save(product:Product) = {
    DB.withConnection{implicit c =>
      SQL("INSERT INTO products(id,name) values({id},{name});")
        .on('id->product.id, 'name -> product.name)
        .executeInsert()
    }
  }

  def update(product:Product) = {
    DB.withConnection{implicit c =>
      SQL("UPDATE products set name = {name} where id={id};")
        .on('id->product.id, 'name -> product.name)
        .executeUpdate()
    }
  }

  def delete(id:Long) = {
    DB.withConnection{implicit c=>
      SQL("DELETE FROM  products where id={id};")
        .on('id->id).executeUpdate()
    }
  }

  def get(id:Long) = {
    DB.withConnection{implicit c=>
      SQL("SELECT * FROM  products where id={id};")
        .on('id->id).executeQuery()
      .singleOpt(defaultParser)
    }
  }

  def all = {
    DB.withConnection{implicit c=>
      SQL("SELECT * FROM  products ;").executeQuery()
        .list(defaultParser)
    }
  }
}
