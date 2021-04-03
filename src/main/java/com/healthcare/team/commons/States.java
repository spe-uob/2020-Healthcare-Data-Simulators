package com.healthcare.team.commons;

public enum States {

    Aberdeenshire("Aberdeenshire"),
    Angus("Angus"),
    Bedfordshire("Bedfordshire"),
    Berkshire("Berkshire"),
    Buckinghamshire("Buckinghamshire"),
    Cambridgeshire("Cambridgeshire"),
    Cheshire("Cheshire"),
    Clackmannanshire("Clackmannanshire"),
    Cornwall("Cornwall"),
    CountyAntrim("County Antrim"),
    CountyArmagh("County Armagh"),
    CountyDown("County Down"),
    CountyFermanagh("County Fermanagh"),
    CountyLondonderry("CountyLondonderry"),
    CountyTyrone("County Tyrone"),
    Cumbria("Cumbria"),
    Derbyshire("Derbyshire"),
    Devon("Devon"),
    Dorset("Dorset"),
    Dunbartonshire("Dunbartonshire"),
    EastLothian("East Lothian"),
    EastSussex("East Sussex"),
    Essex("Essex"),
    Fife("Fife"),
    Gloucestershire("Gloucestershire"),
    Gwynedd("Gwynedd"),
    Herefordshire("Herefordshire"),
    Hertfordshire("Hertfordshire"),
    IsleOfWight("Isle Of Wight"),
    Kent("Kent"),
    Lancashire("Lancashire"),
    Leicestershire("Leicestershire"),
    Lincolnshire("Lincolnshire"),
    Merseyside("Merseyside"),
    Midlothian("Midlothian"),
    Norfolk("Norfolk"),
    NorthYorkshire("North Yorkshire"),
    Northamptonshire("Northamptonshire"),
    Northumberland("Northumberland"),
    Nottinghamshire("Nottinghamshire"),
    Orkney("Orkney"),
    Oxfordshire("Oxfordshire"),
    Powys("Powys"),
    Renfrewshire("Renfrewshire"),
    ShetlandIslands("Shetland Islands"),
    Shropshire("Shropshire"),
    Somerset("Somerset"),
    SouthYorkshire("South Yorkshire"),
    Staffordshire("Staffordshire"),
    Suffolk("Suffolk"),
    Surrey("Surrey"),
    TyneAndWear("TyneAndWear"),
    Warwickshire("Warwickshire"),
    WestLothian("West Lothian"),
    WestMidlands("West Midlands"),
    WestSussex("West Sussex"),
    WestYorkshire("West Yorkshire"),
    Wiltshire("Wiltshire"),
    Worcestershire("Worcestershire");

    private String name;

    States(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
