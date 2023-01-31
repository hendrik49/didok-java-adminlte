$(function () {
    var data = {
        labels : ["January","February","March","April","May","June","July"],
        datasets : [
            {
                fillColor : "rgba(220,220,220,0.5)",
                strokeColor : "rgba(220,220,220,1)",
                pointColor : "rgba(220,220,220,1)",
                pointStrokeColor : "#fff",
                data : [65,59,90,81,56,55,40]
            },
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data : [28,48,40,19,96,27,100]
            }
        ]
    }
    var options = {};
    var ctx = document.getElementById("linechartContainer").getContext('2d');
    new Chart(ctx).Line(data,options);

    var data = [
        {
            value : 30,
            color: "#D97041"
        },
        {
            value : 90,
            color: "#C7604C"
        },
        {
            value : 24,
            color: "#21323D"
        }
    ]

    var ctx = document.getElementById("piechartContainer").getContext('2d');
    new Chart(ctx).Pie(data,options);
});
