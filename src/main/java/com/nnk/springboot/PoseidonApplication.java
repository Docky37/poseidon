package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry class of Poseidon Application that contains the main method.
 *
 * @author Thierry Schreiner
 */
@SpringBootApplication
public class PoseidonApplication {

    /**
     * Main method, entry point of the Poseidon application.
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }

    /**
     * Empty class constructor.
     */
    protected PoseidonApplication() {
    }

}
