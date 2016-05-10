# Contact book API

* [How to run](#how-to-run)
* [RESTful URLs](#restful-urls)

## How to run

Open command line on folder this project and run:

	mvn jooby:run

## RESTful URLs

	GET  /contact/:id - If exist contact with that id, return, else 404.
	POST /contact     - Add a contact with this model: {name: "Anyone", phonenumber: "8288888888"}