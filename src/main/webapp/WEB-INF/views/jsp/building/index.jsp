<%--
  Created by IntelliJ IDEA.
  User: skh
  Date: 2017/7/21
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Impressive Under Construction Flat Responsive Widget Template</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />

    <link rel="icon" href="${pageContext.request.contextPath}/resources/building/images/favicon.ico" type="image/x-icon"/>
    <script type="application/x-javascript">
        addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
        function hideURLbar(){ window.scrollTo(0,1); }
    </script>
    <!-- //for-mobile-apps -->
    <!-- js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/building/js/jquery-2.1.4.min.js"></script>
    <!-- //js -->
    <link href="${pageContext.request.contextPath}/resources/building/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Quicksand:400,300,700' rel='stylesheet' type='text/css'>
</head>
<body>
<!-- banner -->
<div class="banner">
    <div class="banner-info">
        <%--<h1>Impressive<span>Under Construction</span></h1>--%>
       <div>
           <img src="${pageContext.request.contextPath}/resources/building/images/logo.png">
       </div>


        <h2 style="font-size: 37px;margin: 50px auto 50px;">We Are Coming Very Soon!</h2>
        <div class="main-example">
            <div class="countdown-container" id="main-example"></div>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/building/js/jquery.countdown.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/building/js/lodash.min.js"></script>

        <script type="text/template" id="main-example-template">
            <div class="time <\%= label %>">
                <span class="count curr top"><\%= curr %></span>
                <span class="count next top"><\%= next %></span>
                <span class="count next bottom"><\%= next %></span>
                <span class="count curr bottom"><\%= curr %></span>
                <span class="label"><\%= label.length < 6 ? label : label.substr(0, 3)  %></span>
            </div>
        </script>
        <script type="text/javascript">
            $(window).on('load', function() {
                var labels = ['weeks', 'days', 'hours', 'minutes', 'seconds'],
                    nextYear = (new Date().getFullYear() + 1) + '/01/01',
                    template = _.template($('#main-example-template').html()),
                    currDate = '00:00:00:00:00',
                    nextDate = '00:00:00:00:00',
                    parser = /([0-9]{2})/gi,
                    $example = $('#main-example');
                // Parse countdown string to an object
                function strfobj(str) {
                    var parsed = str.match(parser),
                        obj = {};
                    labels.forEach(function(label, i) {
                        obj[label] = parsed[i]
                    });
                    return obj;
                }
                // Return the time components that diffs
                function diff(obj1, obj2) {
                    var diff = [];
                    labels.forEach(function(key) {
                        if (obj1[key] !== obj2[key]) {
                            diff.push(key);
                        }
                    });
                    return diff;
                }
                // Build the layout
                var initData = strfobj(currDate);
                labels.forEach(function(label, i) {
                    $example.append(template({
                        curr: initData[label],
                        next: initData[label],
                        label: label
                    }));
                });
                // Starts the countdown
                $example.countdown(nextYear, function(event) {
                    var newDate = event.strftime('%w:%d:%H:%M:%S'),
                        data;
                    if (newDate !== nextDate) {
                        currDate = nextDate;
                        nextDate = newDate;
                        // Setup the data
                        data = {
                            'curr': strfobj(currDate),
                            'next': strfobj(nextDate)
                        };
                        // Apply the new values to each node that changed
                        diff(data.curr, data.next).forEach(function(label) {
                            var selector = '.%s'.replace(/%s/, label),
                                $node = $example.find(selector);
                            // Update the node
                            $node.removeClass('flip');
                            $node.find('.curr').text(data.curr[label]);
                            $node.find('.next').text(data.next[label]);
                            // Wait for a repaint to then flip
                            _.delay(function($node) {
                                $node.addClass('flip');
                            }, 50, $node);
                        });
                    }
                });
            });
        </script>

        <div class="progressbars" progress="60%"></div>
        <script src="${pageContext.request.contextPath}/resources/building/js/jprogress.js" type="text/javascript"></script>
        <script>
            // activate jprogress
            $(".progressbars").jprogress({
                background: "#80C340"
            });
        </script>
        <div class="social-icons">
            <ul class="social-networks square spin-icon">
                <li><a href="#" class="icon-facebook"></a></li>
                <li><a href="#" class="icon-twitter"></a></li>
                <li><a href="#" class="icon-g-plus"></a></li>
                <li><a href="#" class="icon-dribble"> </a></li>
                <li><a href="#" class="icon-instagram"> </a></li>
                <li><a href="#" class="icon-pinterest"> </a></li>
            </ul>
        </div>
        <div class="copyright">
            <p>© 2017 Factopintal. All rights reserved | Design by <a href="#">factopintal.com</a></p>
        </div>
    </div>
</div>
<!-- //banner -->
</body>
</html>
