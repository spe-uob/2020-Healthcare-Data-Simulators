package com.healthcare.team.csv.objects;

public abstract class CsvPojo {

    @Override
    public abstract String toString();

    public abstract String toCsvString();

    public abstract String getHeaderColumnNames();
}
