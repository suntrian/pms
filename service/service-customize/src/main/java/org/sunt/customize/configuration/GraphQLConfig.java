package org.sunt.customize.configuration;

import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQL graphQL() {
        GraphQLSchema graphQLSchema = GraphQLSchema.newSchema()
                .query(new GraphQLObjectType.Builder()
                        .name("abc")
                        .field(new GraphQLFieldDefinition.Builder()
                                .name("aaa")
                                .type(Scalars.GraphQLInt)
                                .argument(new GraphQLArgument.Builder()
                                        .name("bbb")
                                        .type(Scalars.GraphQLInt)
                                )
                        )
                        .build())
                .build();
        return GraphQL.newGraphQL(graphQLSchema)
                .build();
    }

}
