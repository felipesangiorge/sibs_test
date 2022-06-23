package com.sibs_test.sibs_test_felipe.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.sibs_test.sibs_test_felipe.database.model_entity.BookEntity


@Dao
interface BooksDao {


    @Query(
        """
        DELETE FROM `bookentity`
    """
    )
    fun deleteAll()

    @Query(
        """
        DELETE FROM `bookentity` WHERE book_id = :id
    """
    )
    fun deleteById(id: String)


    @Query(
        """
        SELECT * FROM `bookentity` WHERE book_favorite = :favorite
    """
    )
    fun getAllFavoritePagedBooks(favorite: Boolean = true): DataSource.Factory<Int, BookEntity>

    @Query(
        """
        SELECT * FROM `bookentity`
    """
    )
    fun getAllPagedBooks(): DataSource.Factory<Int, BookEntity>

    @Query(
        """
        SELECT * FROM `bookentity` WHERE book_id = :id
    """
    )
    fun getBookById(id: String): LiveData<BookEntity>


    @Query(
        """
        UPDATE OR ABORT 
            `bookentity` 
        SET `book_favorite` = :favoriteState
        WHERE `book_id` = :bookId
    """
    )
    fun bookFavoriteState(bookId: String, favoriteState: Boolean)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(obj: BookEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(obj: List<BookEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: BookEntity?): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(entity: BookEntity?)

    @Transaction
    fun upsert(entity: BookEntity?) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }
}