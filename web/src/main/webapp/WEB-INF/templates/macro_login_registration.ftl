<#macro login_registration title>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Lobster&display=swap&subset=cyrillic" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Marck+Script&display=swap&subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/css/login_registration.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/button.css">
    <link rel="icon" type="images/png" href="/resources/img/app_img/favicon.png">
    <title>${title}</title>
</head>
<body>

<div class="login_one_container" >

    <div class="container-fluid">
        <div class="row" style="height: 90%" >
            <@content/>
        </div>

        <div class="row justify-content-between" style="height: 10%" >

            <div class="col-lg-10">
            </div>
            <div class="col-lg-2">

                <div class="back_button">
                    <form action="/welcome">
                        <button class="myButton">Назад</button>
                    </form>
                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>

</#macro>