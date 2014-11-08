includeTargets << grailsScript('_GrailsBootstrap')

target(demoGormFromGant: "demonstrate that GORM can be used from a Gant script") {
    depends compile, bootstrap
    def load = grailsApp.classLoader.&loadClass
    def Person = load('demo.Person')
    println 'The demo.Person class loaded successfully'

    Person.withTransaction { tx ->
        // demonstrate that the count method works...
        def numberOfPeople = Person.count()
        println "The number of people initially in the database: ${numberOfPeople}"

        // demonstrate that the save method works...
        ['Jeff', 'Jake', 'Zack', 'Betsy'].each { name ->
            Person.newInstance(firstName: name).save(failOnError: true)
            println "Saved $name to the database."
        }
        numberOfPeople = Person.count()
        println "The number of people in the database after saving those people: ${numberOfPeople}"

        // demonstrate that dynamic finders work
        def people = Person.findAllByFirstNameLike('J%')
        println "The number of people in the database with a name beginning with 'J': ${people?.size()}"

        println 'When the script terminates all of the people will be gone because the application is currently using an in memory database.'
    }
}

setDefaultTarget(demoGormFromGant)
