<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_reff>
    <@libra_reff.libra_menu/>

</#macro>

<#macro content_3>

     <div class="add_book_output">

         <h1>Редактирование данных о книге</h1>

         <form action="/libra/edit_book" method="post" >

             <div class="info_book">



                 <div class="info_enter">
                     <p>ID автора/-ов (ID разделять "#" : 111#222)</p>
                     <input type="text" name="authorsID" placeholder="ID">
                 </div>

                 <div class="info_enter">
                     <p>Название книги</p>
                     <input type="text" name="title" placeholder="${book.title}">
                 </div>

                 <div class="info_enter">
                     <p>Жанр</p>

                     <p>
                         <select name="genre">
                             <option >Выберите жанр</option>
                             <option  value="DETECTIVE">Детектив</option>
                             <option  value="HISTORICAL_NOVEL">Исторический роман</option>
                             <option  value="LOVE_STORY">Любовный роман</option>
                             <option  value="MYSTIC">Мистика</option>
                             <option  value="ADVENTURES">Приключение</option>
                             <option  value="THRILLER">Триллер</option>
                             <option  value="COUNTERCULTURE">Контркультура</option>
                             <option  value="FANTASY">Фэнтези</option>
                             <option  value="MUSIC">Музыка</option>
                             <option  value="EDUCATION_LITERATURE">Обучающая литература</option>
                             <option  value="JOURNALISM">Публицистика</option>
                             <option  value="SCIENTIFIC_LITERATURE">Научная литература</option>
                             <option  value="SCIENCE_FICTION">Научная фантастика</option>

                         </select>
                     </p>
                 </div>

                 <div class="info_enter">
                     <p>Издательство</p>
                     <input type="text" name="publishedName" placeholder="${book.publishedName}">
                 </div>

                 <div class="info_enter">
                     <p>Год издания</p>
                     <input type="text" name="yearOfPublishing" placeholder="${book.yearOfPublishing}">
                 </div>

                 <div class="info_enter">
                     <p>Количество страниц</p>
                     <input type="text" name="pageCount" placeholder="${book.pageCount}">
                 </div>

                 <div class="info_enter">
                     <p>Номер ISBN</p>
                     <input type="text" name="ISBN" placeholder="${book.ISBN}">
                 </div>

                 <div class="info_enter">
                     <p>Количество</p>
                     <input type="text" name="printEditionCount" placeholder="${book.printEditionCount}">
                 </div>

                 <div class="info_enter_file">


                     <div class="file_1">
                         <p>Загрузка обложки книги</p>
                         <input type="file" name="imagePath" >
                     </div>


                 </div>


             </div>


             <div class="book_discription">

                 <p>
                     <textarea name="bookDescription" rows="12" placeholder="${book.bookDescription}"></textarea>
                 </p>

             </div>

             <div class="add_button">

                 <button class="addButton">Изменить</button>

             </div>


         </form>
     </div>


</#macro>

<@emplouee_room_menu title = "edit book" />