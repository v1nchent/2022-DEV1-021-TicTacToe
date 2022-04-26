import Tile from "./tile";

const Grid = (props) => {
    return (
        <div className="Grid">
            <div className="Grid-row">
                <Tile
                    index={[0,0]}
                    value={props.gridArray[0][0]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[0,1]}
                    value={props.gridArray[0][1]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[0,2]}
                    value={props.gridArray[0][2]}
                    onTilePress={props.onTilePress}
                />
            </div>
            <div className="Grid-row">
                <Tile
                    index={[1,0]}
                    value={props.gridArray[1][0]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[1,1]}
                    value={props.gridArray[1][1]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[1,2]}
                    value={props.gridArray[1][2]}
                    onTilePress={props.onTilePress}
                />
            </div>
            <div className="Grid-row">
                <Tile
                    index={[2,0]}
                    value={props.gridArray[2][0]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[2,1]}
                    value={props.gridArray[2][1]}
                    onTilePress={props.onTilePress}
                />
                <Tile
                    index={[2,2]}
                    value={props.gridArray[2][2]}
                    onTilePress={props.onTilePress}
                />
            </div>
        </div>
    );
}
export default Grid;