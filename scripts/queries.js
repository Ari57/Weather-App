//const Pool = require("pg").Pool
const pool = require("./credentials")

const getUsers = (request, response) => {
    pool.query("SELECT start, dest FROM JOURNEYLOCATIONS", (error, results) => {
        if (error) {
            throw error
        }
        response.status(200).json(results.rows)
    })
}

const addLocation = (request, response) => {
    const {location} = request.body

    pool.query('INSERT INTO start (location) VALUES ($1) ', [location], (error, results) => {
        if (error) {
            throw error
        }
        response.status(201).send("Location added")
    })
}

const updateUser = (request, response) => {
    const id = parseInt(request.params.id)
    const { name, email } = request.body
  
    pool.query(
      'INSERT INTO JOURNEYLOCATIONS (start, dest) VALUES($1, $1)', ["ho"],
      (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).send(`User modified with ID: ${id}`)
      }
    )
}

module.exports = {
    getUsers,
    addLocation,
    updateUser
}