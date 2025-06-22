package org.llin.demo.twelvequotes.respository;

import org.llin.demo.twelvequotes.model.SingleQuote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleQuoteRepository extends CrudRepository<SingleQuote,Integer> {
	
		
}
