const express = require("express")
const bodyParser = require("body-parser")
const app = express()
const port = 3000
const {con: con} = require("./credentials")
var path = require("path")

var urlencodedParser = bodyParser.urlencoded({ extended: false});

app.use(express.static('public'));


app.get("/success", function (req, res) {
    res.sendFile(path.join(__dirname, '../public', 'success.html'));
})

app.get("/fail", function (req, res) {
  res.sendFile(path.join(__dirname, '../public', 'fail.html'));
})

app.post('/location', urlencodedParser, function(req, res){
  var sql = "INSERT INTO APIDATA (Location) VALUES ('"+req.body.locationName+"')"
  con.query(sql, function (err, result) {
    if (err) {
      res.redirect("/fail")
      throw err 
    }
    console.log(req.body.locationName)
    res.redirect("/success")
  })
})


app.listen(port, () => {
    console.log(`App running on port ${port}`)
})
