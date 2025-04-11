package com.tib.ts.mod.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tib.ts.mod.config.DynamicSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class SemanticArtefactDistribution extends Distribution {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactDistributionId;
	
	@JsonProperty("@type")
	private Object semanticArtefactDistributionType;

	@JsonProperty("useGuidelines")
	private Object useGuidelines;

	@JsonProperty("created")
	private Object created;

	@JsonProperty("dateSubmitted")
	private Object dateSubmitted;

	@JsonProperty("valid")
	private Object valid;

	@JsonProperty("authorProperty")
	private Object authorProperty;

	@JsonProperty("averageChildCount")
	private Object averageChildCount;

	@JsonProperty("browsingUI")
	private Object browsingUI;

	@JsonProperty("classesWithMoreThan25Children")
	private Object classesWithMoreThan25Children;

	@JsonProperty("classesWithNoAuthorMetadata")
	private Object classesWithNoAuthorMetadata;

	@JsonProperty("classesWithNoDateMetadata")
	private Object classesWithNoDateMetadata;

	@JsonProperty("classesWithNoDefinition")
	private Object classesWithNoDefinition;

	@JsonProperty("classesWithNoFormalDefinition")
	private Object classesWithNoFormalDefinition;

	@JsonProperty("classesWithNoLabel")
	private Object classesWithNoLabel;

	@JsonProperty("classesWithOneChild")
	private Object classesWithOneChild;

	@JsonProperty("conformsToKnowledgeRepresentationParadigm")
	private Object conformsToKnowledgeRepresentationParadigm;

	@JsonProperty("createdProperty")
	private Object createdProperty;

	@JsonProperty("curatedOn")
	private Object curatedOn;

	@JsonProperty("definitionProperty")
	private Object definitionProperty;

	@JsonProperty("fairAssessment")
	private Object fairAssessment;

	@JsonProperty("fairScore")
	private Object fairScore;

	@JsonProperty("hasRepresentationLanguage")
	private Object hasRepresentationLanguage;

	@JsonProperty("hasSyntax")
	private Object hasSyntax;

	@JsonProperty("hierarchyProperty")
	private Object hierarchyProperty;

	@JsonProperty("maxChildCount")
	private Object maxChildCount;

	@JsonProperty("maxDepth")
	private Object maxDepth;

	@JsonProperty("metadataVoc")
	private Object metadataVoc;

	@JsonProperty("metrics")
	private Object metrics;

	@JsonProperty("modifiedProperty")
	private Object modifiedProperty;

	@JsonProperty("numberOfAxioms")
	private Object numberOfAxioms;

	@JsonProperty("numberOfClasses")
	private Object numberOfClasses;

	@JsonProperty("numberOfDataProperties")
	private Object numberOfDataProperties;

	@JsonProperty("numberOfDeprecated")
	private Object numberOfDeprecated;

	@JsonProperty("numberOfIndividuals")
	private Object numberOfIndividuals;

	@JsonProperty("numberOfLabels")
	private Object numberOfLabels;

	@JsonProperty("numberOfMappings")
	private Object numberOfMappings;

	@JsonProperty("numberOfObjectProperties")
	private Object numberOfObjectProperties;

	@JsonProperty("numberOfProperties")
	private Object numberOfProperties;

	@JsonProperty("obsoleteParent")
	private Object obsoleteParent;

	@JsonProperty("obsoleteProperty")
	private Object obsoleteProperty;

	@JsonProperty("prefLabelProperty")
	private Object prefLabelProperty;

	@JsonProperty("sampleQueries")
	private Object sampleQueries;

	@JsonProperty("synonymProperty")
	private Object synonymProperty;

	@JsonProperty("usedEngineeringMethodology")
	private Object usedEngineeringMethodology;

	@JsonProperty("deprecated")
	private Object deprecated;

	@JsonProperty("imports")
	private Object imports;

	@JsonProperty("endpoint")
	private Object endpoint;

	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactDistributionType != null ? semanticArtefactDistributionType.toString() : "mod:SemanticArtefactDistribution";
    }
}
