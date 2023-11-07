
create database DUAN1



GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Colors]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]




GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Product_Details]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Thumnail][NVARCHAR](30),
    [Color_Id] [INT] NOT NULL,
    [Product_id] [INT] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Activated] [BIT] NOT NULL,
    [Product_price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[QRS]
(
    [Qr_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Image] [NVARCHAR](50) NOT NULL,
    [Product_Details_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Qr_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Type_Products]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Brands]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Designs]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Total_Powers]
(
    [Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Frequency_Ranges]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO




CREATE TABLE [dbo].[Products]
(
    [Product_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT] NOT NULL,
    [Thumnail] [NVARCHAR](30) NOT NULL,
    [Total_Quantity] [INT] NOT NULL,
	[Frequency_Range_id] [INT] NOT NULL,
	[Total_Power_Id] [INT] NOT NULL,
    [Brand_Id] [INT] NOT NULL,
    [Type_Product_Id] [INT] NOT NULL,
    [Categorie_Id] [INT] NOT NULL,
    [Design_Id] [INT] NOT NULL,
    [Updated_Time] [DATE] NOT NULL,
    [Account_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Product_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Categories]
(
    [Id] [INT] IDENTITY(1,1) ,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Warrantys]
(
    [Warranty_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Duration] [INT] NOT NULL,
    [Start_Date] [DATE] NOT NULL,
    [End_Date] [DATE] NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE TABLE [dbo].[Product_Warranlys]
(
    [Product_Warranly_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Product_Id] [INT] NOT NULL,
    [Warranty_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Invoices_Details]
(
    [Invoice_Detail_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Invoice_Id] [INT] NOT NULL,
    [Product_Id] [INT] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Invoice_Detail_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Code_Coupons]
(
    [Code_Coupons_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Code] [INT] NOT NULL,
    [Activated] [BIT]NOT NULL,
    [Coupons_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Code_Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Coupons]
(
    [Coupons_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Describe] [NVARCHAR](100) NOT NULL,
    [Start_Day] [DATE] NOT NULL,
    [End_Date] [DATE] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Promotional_Type] [BIT] NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Attribute] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Pays]
(
    [Pay_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Pay_Method_Id] [VARCHAR](10) NOT NULL,
    [Clent_Id] [INT] NOT NULL,
    [Invoice_Id] [INT] NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Money] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Pay_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE TABLE [dbo].[Invoices]
(
    [Invoice_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Created_Time] [DATE] NOT NULL,
    [Account_Id] [INT] NOT NULL,
    [Client_Id] [INT] NOT NULL,
    [Origin_Price] [FLOAT] NOT NULL,
    [Attribute_Discount] [NVARCHAR](50) NOT NULL,
    [Note] [NVARCHAR](50) NOT NULL,
    [Status_Invoice_Id] [VARCHAR](10) NOT NULL,
    [Total_Quantity] [INT] NOT NULL,
    [Discount_Price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Users]
(
    [User_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Birthday] [DATE] NOT NULL,
    [Gender] [NVARCHAR](20) NOT NULL,
    [Phone_number] [NVARCHAR](10) NOT NULL,
    [Address] [NVARCHAR](50) NOT NULL,
    [Email] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[User_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Account]
(
    [Account_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Username] [NVARCHAR](30) NOT NULL,
    [Password] [NVARCHAR](30) NOT NULL,
    [User_Id] [INT] NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Role_Id] [VARCHAR](5) NOT NULL,
    [Update_Day] [DATE] NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Account_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Status_Invoice]
(
    [Status_Invoice_Id] [VARCHAR](10) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Status_Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Client]
(
    [Client_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Phone_Number] [NVARCHAR](10) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Client_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Pay_Method]
(
    [Pay_Method_Id] [VARCHAR](10) NOT NULL,
    [Method] [NVARCHAR](30) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    [Content] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Pay_Method_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Role]
(
    [Id] [VARCHAR](5) NOT NULL,
   [Activated] [Bit] NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Store]
(
    [Store_Id] [VARCHAR](10) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Address] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Store_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



ALTER TABLE Product_Details
ADD CONSTRAINT FK_Products_Product_Details
FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id);

ALTER TABLE Product_Details
ADD CONSTRAINT FK_Colors_Product_Details
FOREIGN KEY (Color_Id) REFERENCES Colors(Id);


ALTER TABLE Products
ADD CONSTRAINT FK_Type_Product_Products
FOREIGN KEY (Type_Product_Id) REFERENCES Type_Products(Id);

ALTER TABLE Products
ADD CONSTRAINT frequency_ranges_Product
FOREIGN KEY (Frequency_Range_id) REFERENCES frequency_ranges(Id);

ALTER TABLE Products
ADD CONSTRAINT FK_total_powers
FOREIGN KEY (Total_Power_Id) REFERENCES total_powers(Id);

ALTER TABLE Products
ADD CONSTRAINT FK_Products_Brands
FOREIGN KEY (Brand_Id) REFERENCES Brands(Id);

ALTER TABLE Products
ADD CONSTRAINT FK_Products_Categories
FOREIGN KEY (Categorie_Id) REFERENCES Categories(Id);

ALTER TABLE Products
ADD CONSTRAINT FK_Products_Designs
FOREIGN KEY (Design_Id) REFERENCES Designs(Id);

ALTER TABLE Products
ADD CONSTRAINT FK_Products_Account
FOREIGN KEY (Account_Id) REFERENCES Account(Account_Id);

ALTER TABLE Product_Warranlys
ADD CONSTRAINT FK_Products_Warranly_Product
FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id);

ALTER TABLE Product_Warranlys
ADD CONSTRAINT FK_Product_Warranly_Warranly
FOREIGN KEY (Warranty_Id) REFERENCES Warrantys(Warranty_Id);


ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);


ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Product
FOREIGN KEY (Product_Id) REFERENCES Products(Product_Id);


ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Client
FOREIGN KEY (Client_Id) REFERENCES Client(Client_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Status_Invoices
FOREIGN KEY (Status_Invoice_Id) REFERENCES Status_Invoice(Status_Invoice_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Acount
FOREIGN KEY (Account_Id) REFERENCES Account(Account_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Pay_Method
FOREIGN KEY (Pay_Method_Id) REFERENCES Pay_Method(Pay_Method_Id);


ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Client
FOREIGN KEY (Clent_Id) REFERENCES Client(Client_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);


ALTER TABLE Account
ADD CONSTRAINT FK_Account_Users
FOREIGN KEY (User_Id) REFERENCES Users(User_Id);

ALTER TABLE Account
ADD CONSTRAINT FK_Account_Role
FOREIGN KEY (Role_Id) REFERENCES Role(Id);



INSERT INTO Brands
    (Name, Date_Created, Description, Activated)
VALUES
    ( 'Brand 1', '2023-11-02', 'Description 1', 1),
    ( 'Brand 2', '2023-11-02', 'Description 2', 1),
    ( 'Brand 3', '2023-11-02', 'Description 3', 1),
    ( 'Brand 4', '2023-11-02', 'Description 4', 1),
    ( 'Brand 5', '2023-11-02', 'Description 5', 1),
    ( 'Brand 6', '2023-11-02', 'Description 6', 0),
    ( 'Brand 7', '2023-11-02', 'Description 7', 1),
    ( 'Brand 8', '2023-11-02', 'Description 8', 1),
    ( 'Brand 9', '2023-11-02', 'Description 9', 1),
    ( 'Brand 10', '2023-11-02', 'Description 10', 1),
    ( 'Brand 11', '2023-11-02', 'Description 11', 1),
    ( 'Brand 12', '2023-11-02', 'Description 12', 1),
    ( 'Brand 13', '2023-11-02', 'Description 13', 0),
    ( 'Brand 14', '2023-11-02', 'Description 14', 1),
    ( 'Brand 15', '2023-11-02', 'Description 15', 1);

INSERT INTO Designs
    ( Name,Date_Created , Description, Activated)
VALUES
    ( 'Design 1', '2023-11-02',   'Description 1', 1),
    ( 'Design 2', '2023-11-02',   'Description 2', 1),
    ( 'Design 3', '2023-11-02',   'Description 3', 1),
    ( 'Design 4', '2023-11-02',   'Description 4', 1),
    ( 'Design 5', '2023-11-02',   'Description 5', 1),
    ( 'Design 6', '2023-11-02',   'Description 6', 0),
    ( 'Design 7', '2023-11-02',   'Description 7', 1),
    ( 'Design 8', '2023-11-02',   'Description 8', 1),
    ( 'Design 9', '2023-11-02',   'Description 9', 1),
    ( 'Design 10', '2023-11-02',  'Description 10', 1),
    ( 'Design 11', '2023-11-02',  'Description 11', 1),
    ( 'Design 12', '2023-11-02',  'Description 12', 1),
    ( 'Design 13', '2023-11-02',  'Description 13', 0),
    ( 'Design 14', '2023-11-02',  'Description 14', 1),
    ( 'Design 15', '2023-11-02',  'Description 15', 1);

INSERT INTO [dbo].[Total_Powers] ([Name], [Date_Created], [Description], [Activated]) VALUES
('10 Watts', '2021-12-01', 'Speakers with a total power output of 10 watts', 1),
('20 Watts', '2021-12-02', 'Speakers with a total power output of 20 watts', 1),
('30 Watts', '2021-12-03', 'Speakers with a total power output of 30 watts', 1),
('40 Watts', '2021-12-04', 'Speakers with a total power output of 40 watts', 1),
('50 Watts', '2021-12-05', 'Speakers with a total power output of 50 watts', 1),
('60 Watts', '2021-12-06', 'Speakers with a total power output of 60 watts', 1),
('70 Watts', '2021-12-07', 'Speakers with a total power output of 70 watts', 0),
('80 Watts', '2021-12-08', 'Speakers with a total power output of 80 watts', 1),
('90 Watts', '2021-12-09', 'Speakers with a total power output of 90 watts', 1),
('100 Watts', '2021-12-10', 'Speakers with a total power output of 100 watts', 1),
('200 Watts', '2021-12-11', 'Speakers with a total power output of 200 watts', 1),
('300 Watts', '2021-12-12', 'Speakers with a total power output of 300 watts', 0),
('400 Watts', '2021-12-13', 'Speakers with a total power output of 400 watts', 1),
('500 Watts', '2021-12-14', 'Speakers with a total power output of 500 watts', 1),
('1000 Watts', '2021-12-15', 'Speakers with a total power output of 1000 watts', 1)

	go
INSERT INTO [dbo].[Frequency_Ranges] ([Name], [Date_Created], [Description], [Activated]) VALUES
('Full range', '2021-12-01', 'Speakers that can reproduce sound across the entire frequency spectrum', 1),
('Subwoofers', '2021-12-02', 'Speakers that produce low frequency sounds, often used for bass', 1),
('Tweeters', '2021-12-03', 'Speakers that produce high frequency sounds, often used for treble', 1),
('Midrange Speakers', '2021-12-04', 'Speakers that reproduce sounds in the midrange frequency spectrum', 1),
('Woofers', '2021-12-05', 'Speakers that reproduce sounds in the lower frequency range than midrange', 1),
('Sub-midrange Speakers', '2021-12-06', 'Speakers that reproduce sound in the sub-midrange frequency range', 0),
('Super-tweeters', '2021-12-07', 'Speakers that produce ultra-high frequency sounds', 0),
('Midbass Drivers', '2021-12-08', 'Speakers that reproduce sound from the midrange to the bass', 1),
('Woofer-tweeter Combo', '2021-12-09', 'Speakers that combine woofers and tweeters for full range sound', 1),
('Midrange-Tweeter Combo', '2021-12-10', 'Speakers that combine midrange and tweeters for clearer sound', 1),
('Subwoofer-Midrange Combo', '2021-12-11', 'Speakers that combine subwoofers and midrange drivers', 1),
('Subwoofer-Tweeter Combo', '2021-12-12', 'Speakers that combine subwoofers and tweeters for richer sound', 0),
('Full Range Array', '2021-12-13', 'Speakers that use multiple drivers to reproduce the full range of sound', 1),
('Dual-midrange Speakers', '2021-12-14', 'Speakers that use two midrange drivers for better sound reproduction', 1),
('Full Range Floor Standing', '2021-12-15', 'Speakers that stand on the floor and can produce full range sound', 0)


INSERT INTO [dbo].[Colors] ([Name], [Date_Created], [Description], [Activated]) VALUES
('Red', '2021-12-01', 'A bold and vibrant color', 1),
('Blue', '2021-12-02', 'A calming color often associated with the ocean or sky', 1),
('Green', '2021-12-03', 'A natural color often associated with growth and renewal', 1),
('Yellow', '2021-12-04', 'A bright and cheerful color often associated with sunshine', 0),
('Purple', '2021-12-05', 'A regal color often associated with luxury and royalty', 1),
('Orange', '2021-12-06', 'A vibrant color often associated with energy and enthusiasm', 0),
('Pink', '2021-12-07', 'A soft and romantic color often associated with femininity', 1),
('Black', '2021-12-08', 'A bold and dramatic color often associated with elegance and sophistication', 1),
('White', '2021-12-09', 'A pure and clean color often associated with innocence and simplicity', 1),
('Gray', '2021-12-10', 'A neutral color often associated with stability and balance', 0),
('Brown', '2021-12-11', 'A warm and earthy color often associated with nature and comfort', 1),
('Beige', '2021-12-12', 'A subtle and versatile color often associated with sophistication and simplicity', 0),
('Turquoise', '2021-12-13', 'A bright and refreshing color often associated with the tropics', 1),
('Gold', '2021-12-14', 'A luxurious color often associated with wealth and prosperity', 1),
('Silver', '2021-12-15', 'A metallic color often associated with elegance and harmony', 0)

INSERT INTO [dbo].[Categories] ([Name], [Date_Created], [Description], [Activated]) VALUES
('Speakers for Computers', '2021-12-01', 'Speakers designed for use with computers', 1),
('Home Theater Speakers', '2021-12-02', 'Speakers designed for home theater use', 1),
('Portable Speakers', '2021-12-03', 'Compact speakers designed for portable use', 1),
('Bluetooth Speakers', '2021-12-04', 'Speakers that connect to devices via Bluetooth', 0),
('Gaming Speakers', '2021-12-05', 'Speakers designed for use with gaming systems', 1),
('Wireless Speakers', '2021-12-06', 'Speakers that connect to devices wirelessly', 0),
('Bookshelf Speakers', '2021-12-07', 'Small speakers designed to fit on bookshelves', 1),
('Floor-standing Speakers', '2021-12-08', 'Tall speakers designed to stand on the floor', 1),
('Soundbars', '2021-12-09', 'Long and narrow speakers designed to sit below a TV', 1),
('Smart Speakers', '2021-12-10', 'Speakers with voice assistant capabilities', 0),
('In-ceiling Speakers', '2021-12-11', 'Speakers designed to be installed in ceilings', 1),
('In-wall Speakers', '2021-12-12', 'Speakers designed to be installed in walls', 0),
('Satellite Speakers', '2021-12-13', 'Small and compact speakers designed to be placed anywhere for surround sound', 1),
('Studio Monitors', '2021-12-14', 'Speakers designed for use in music production', 1),
('Outdoor Speakers', '2021-12-15', 'Speakers designed for use outside', 0)

INSERT INTO [dbo].[Type_Products] ([Name], [Date_Created], [Description], [Activated]) VALUES
('Wireless Speakers', '2021-12-01', 'Speakers that connect to devices wirelessly', 1),
('Bluetooth Speakers', '2021-12-02', 'Speakers that connect to devices via Bluetooth', 1),
('Wired Speakers', '2021-12-03', 'Speakers that require a physical connection to a device', 1),
('Portable Speakers', '2021-12-04', 'Compact speakers designed for portable use', 0),
('Home Theater Speakers', '2021-12-05', 'Speakers designed for home theater use', 1),
('Computer Speakers', '2021-12-06', 'Speakers designed for use with computers', 1),
('Gaming Speakers', '2021-12-07', 'Speakers designed for use with gaming systems', 1),
('Smart Speakers', '2021-12-08', 'Speakers with voice assistant capabilities', 0),
('Soundbars', '2021-12-09', 'Long and narrow speakers designed to sit below a TV', 1),
('Bookshelf Speakers', '2021-12-10', 'Small speakers designed to fit on bookshelves', 1),
('Floor-standing Speakers', '2021-12-11', 'Tall speakers designed to stand on the floor', 1),
('In-ceiling Speakers', '2021-12-12', 'Speakers designed to be installed in ceilings', 0),
('In-wall Speakers', '2021-12-13', 'Speakers designed to be installed in walls', 1),
('Satellite Speakers', '2021-12-14', 'Small and compact speakers designed for surround sound', 1),
('Outdoor Speakers', '2021-12-15', 'Speakers designed for use outside', 0)