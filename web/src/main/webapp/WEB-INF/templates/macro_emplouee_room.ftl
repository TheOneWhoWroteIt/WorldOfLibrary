<#macro emplouee_room_menu title >

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap&subset=cyrillic" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Marck+Script&display=swap&subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/css/button.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/emplouee_room_menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/libra_menu.css">
    <link rel="icon" type="images/png" href="/resources/img/app_img/favicon.png">
    <title>${title}</title>
</head>
<body>

<div class="container-fluid" style="padding: 0">

    <div class="emplouee_menu_one_container" >
        <div class="row" style="height: 100%">
            <div class="col-lg-8">

                <div class="emplouee_menu_two_container">
                    <div class="white_fon">


                    </div>

                    <div class="emplouee_content">
                        <div class="content">
                            <@content_3/>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-lg-4">

                <div class="row" style="height: 10%">
                    <div class="col-lg" style="text-align: right">

                        <div class="out_button">
                        <form action="/logout" method="post">
                            <button class="outButton">Выйти</button>
                        </form>
                        </div>

                    </div>
                </div>

                <div class="row" style="height: 30%">
                    <div class="col-lg">

                        <div class="emplouee_info">
                            <div class="white_fon">
                            <p>

                                Пользователь: ${user.firstName} ${user.lastName} <br>
                                Должность: ${user.position}<br>
                                Табельный номер: №${user.personnelNumber}

                            </p>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="row" style="height: 60%">
                    <div class="col-lg">

                        <div class="menu_references" >

                            <div class="white_fon">

                            </div>

                            <div class="references_for_action">

                                <div class="references">
                                <@content_2/>
                                </div>

                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>

</div>
</body>

</#macro>