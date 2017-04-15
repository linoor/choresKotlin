package pl.mpomaran

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

/**
 * Created by linoor on 15.04.17.
 */

@CrossOrigin(origins = arrayOf("http://localhost:3000"))
@RestController
class ChoreController (val choreRepository: ChoreRepository) {

}
