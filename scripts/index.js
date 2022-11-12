const express = require("express")
const bodyParser = require("body-parser")
const app = express()
const port = 3000
const {pool: pool} = require("./credentials")

var urlencodedParser = bodyParser.urlencoded({ extended: false});

app.use(express.static('public'));
app.get('/index.html', function(req, res){
    res.sendFile(_dirname + "/" + "index.html");
})

app.post('/location', urlencodedParser, function(req, res){
    response = { "Posting following data to DB " : req.body.locationName }
    pool.query(
        'INSERT INTO JOURNEYLOCATIONS (start, dest) VALUES($1, $1)', [req.body.locationName],
        (error, results) => {
          if (error) {
            throw error
          }
        }
      )
    console.log(req.body.locationName)
    res.end(JSON.stringify(response));
})

app.get('/location/get', urlencodedParser, function(req, res){
    var receivedLocation = req.query.locationName
})


app.listen(port, () => {
    console.log(`App running on port ${port}`)
})
