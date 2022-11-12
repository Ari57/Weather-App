const express = require("express")
const bodyParser = require("body-parser")
const app = express()
const port = 3000
const db = require("./queries")

var urlencodedParser = bodyParser.urlencoded({ extended: false});


app.use(express.static('public'));
app.get('/index.html', function(req, res){
    res.sendFile(_dirname + "/" + "index.html");
})

app.post('/location', urlencodedParser, function(req, res){
    response = { LocationName : req.body.locationName };
    console.log(response);
    console.log(req.body.locationName)
    res.end(JSON.stringify(response));
})

app.get('/location/get', urlencodedParser, function(req, res){
    var receivedLocation = req.query.locationName
})

//app.get("/users", db.getLocation)
//app.post("/location/get", db.addLocation)

app.listen(port, () => {
    console.log(`App running on port ${port}`)
})
