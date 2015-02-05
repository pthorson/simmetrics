/*
 * SimMetrics - SimMetrics is a java library of Similarity or Distance Metrics,
 * e.g. Levenshtein Distance, that provide float based similarity measures
 * between String Data. All metrics return consistent measures rather than
 * unbounded similarity scores.
 * 
 * Copyright (C) 2014 SimMetrics authors
 * 
 * This file is part of SimMetrics. This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * SimMetrics. If not, see <http://www.gnu.org/licenses/>.
 */

package org.simmetrics.tokenizers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.simmetrics.wordhandlers.GenericStopTermHandler;
import org.simmetrics.wordhandlers.TermHandler;

import com.google.common.base.Predicate;

import static com.google.common.collect.Collections2.filter;

public class TermFilterTokenizer extends AbstractTokenizer {

	private Tokenizer tokenizer = new WhitespaceTokenizer();

	private TermHandler termHandler = new GenericStopTermHandler();

	public TermHandler getTermHandler() {
		return termHandler;
	}

	public void setTermHandler(TermHandler termHandler) {
		this.termHandler = termHandler;
	}

	public Tokenizer getTokenizer() {
		return tokenizer;
	}

	public void setTokenizer(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	private final Predicate<String> isWord = new Predicate<String>() {

		@Override
		public boolean apply(String input) {
			return termHandler.isWord(input);
		}
	};

	@Override
	public ArrayList<String> tokenizeToList(String input) {
		return new ArrayList<>(filter(tokenizer.tokenizeToList(input),
				isWord));
	}

	@Override
	public Set<String> tokenizeToSet(String input) {
		return new HashSet<>(filter(tokenizer.tokenizeToSet(input),
				isWord));
	}

}