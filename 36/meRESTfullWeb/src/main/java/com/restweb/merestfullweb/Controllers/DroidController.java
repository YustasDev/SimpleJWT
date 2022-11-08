package com.restweb.merestfullweb.Controllers;

import com.restweb.merestfullweb.dto.Droid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/droid")
    class DroidController {
        private final Droid droid;

        public DroidController(Droid droid) {
            this.droid = droid;
        }

        @GetMapping
        Droid getDroid() {
            return droid;
        }
    }

