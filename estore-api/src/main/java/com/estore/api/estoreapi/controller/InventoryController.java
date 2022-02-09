package com.estore.api.estoreapi.controller;

public class InventoryController {
    





    /**
     * Updates the {@linkplain Product product} with the provided {@linkplain Product product} object, if it exists
     * 
     * @param hero The {@link Product product} to update
     * 
     * @return ResponseEntity with updated {@link Product product} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @author Holden Lalumiere
     */
    @PutMapping("")
    public ResponseEntity<Product> updateHero(@RequestBody Product product) {
        LOG.info("PUT /heroes " + product);

        try {
            if(productDao.updateHero(product) != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
