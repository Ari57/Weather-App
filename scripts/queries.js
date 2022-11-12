//const Pool = require("pg").Pool
const {pool: pool} = require("./credentials")

const getLocation = (request, response) => {
    pool.query("SELECT start, dest FROM JOURNEYLOCATIONS", (error, results) => {
        if (error) {
            throw error
        }
        response.status(200).json(results.rows)
    })
}

const addLocation = (request, response) => {
  
    pool.query(
      'INSERT INTO JOURNEYLOCATIONS (start, dest) VALUES($1, $1)', [request.body.locationName],
      (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).send(`User modified`)
      }
    )
}



module.exports = {
    getLocation,
    addLocation,
}