<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_reff>
    <@libra_reff.libra_menu/>

</#macro>

<#macro content_3>

    <div class="libra_book_info">
        <div class="row" style="height: 92%">
            <div class="col-lg-8">
                <div class="author_info_block">
                    <h1>${author.firstName} ${author.lastName}</h1>
                    <h2>ID - ${author.id}</h2>
                    <div class="author_info" style="overflow: auto">
                        <p>
                            ${author.authorDescription}
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="author_book_block">
                    <h1>Книги автора</h1>
                    <div class="author_info">
                        <p>

                            <#list author.books as book>
                        <form action="/libra/book_info" method="get">
                            <button class="authornameButton" name="bookId" value="${book.id}" style="font-size: 20px">${book.title}</button>
                        </form>
                            </#list>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="height: 8%">
            <div class="col-lg">
                <div class="edit_book_button">
                    <form action="/libra/edit_author" method="get">
                        <button class="myButton" name="authorId" value="${author.id}">Редактировать</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</#macro>

<@emplouee_room_menu title = "author info" />