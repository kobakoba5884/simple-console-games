package simple.games.services;

import lombok.Data;

@Data
public abstract class AbstractGame implements Game{
    protected String gameName;
    protected String gameDescription;
}
